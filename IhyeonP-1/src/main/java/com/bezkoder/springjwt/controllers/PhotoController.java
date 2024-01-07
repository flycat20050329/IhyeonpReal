package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.Photo;
import com.bezkoder.springjwt.models.PhotoHeart;
import com.bezkoder.springjwt.models.PhotoPost;
import com.bezkoder.springjwt.models.PhotoReply;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.PhotoHeartRepository;
import com.bezkoder.springjwt.repository.PhotoPostRepository;
import com.bezkoder.springjwt.repository.PhotoReplyRepository;
import com.bezkoder.springjwt.repository.PhotoRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/photo")
public class PhotoController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PhotoRepository photoRepository;

	@Autowired
	PhotoPostRepository photoPostRepository;

	@Autowired
	PhotoReplyRepository photoReplyRepository;

	@Autowired
	PhotoHeartRepository photoHeartRepository;

	@GetMapping("/getAllPhoto")
	public List<Photo> getAllPhoto() {
		return photoRepository.findAll().stream().sorted(Comparator.comparing(Photo::getId).reversed())
				.collect(Collectors.toList());
//		return imageRepository.findAll();
	}

	@GetMapping("/getClickedPhotoData/{id}")
	public Map<String, Object> getClickedPhotoData(@PathVariable Long id) {
		Map<String, Object> photoData = new HashMap<String, Object>();

		photoData.put("images", photoRepository.findByPhotoPostId(id));
		photoData.put("post", photoPostRepository.findById(id));

		return photoData;
	}

	@GetMapping("/getPhotoFilteredDate/{startDate}/{endDate}")
	public List<Photo> getPhotoFilteredDate(@PathVariable String startDate, @PathVariable String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        return photoRepository.findAllByPhotoPostUploadedOnBetween(start, end);
	}

	@GetMapping("/getPhotoPost/{id}")
	public PhotoPost getPhotoPost(@PathVariable Long id) {
		return photoPostRepository.findAllById(id).get(0);
	}

	@GetMapping("/getPhotosByPostId/{id}")

	public List<Photo> getPhotosByPostId(@PathVariable Long id) {
		return photoRepository.findByPhotoPostId(id);
	}

	@PostMapping("/uploadPhoto")
	public List<Photo> uploadPhoto(@RequestParam() List<MultipartFile> images, @RequestParam() Long postId)
			throws IOException {
		PhotoPost photoPost = photoPostRepository.findAllById(postId).get(0);

//		List<Photo> photoList = new ArrayList<Photo>();

		for (int i = 0; i < images.size(); i++) {
			Photo photo = new Photo(images.get(i).getBytes(), photoPost, i);

			photoRepository.save(photo);
//			photoList.add(photo);
		}

		return photoRepository.findAll().stream().sorted(Comparator.comparing(Photo::getId).reversed())
				.collect(Collectors.toList());
	}

	@PostMapping("/uploadPhotoPost")
	public Long uploadPhotoPost(@RequestParam() Long userId, @RequestParam() String text) {
		User user = userRepository.findAllById(userId).get(0);

		PhotoPost photoPost;

		if (text.equals("")) {
			photoPost = new PhotoPost(user, null);
		} else {
			photoPost = new PhotoPost(user, text);
		}

		photoPostRepository.save(photoPost);

		return photoPost.getId();
	}

	@PostMapping("/updatePostText")
	public PhotoPost updatePostText(@RequestParam() Long postId, @RequestParam() String text) {
		PhotoPost photoPost = photoPostRepository.findAllById(postId).get(0);

		photoPost.setText(text);

		photoPostRepository.save(photoPost);

		return photoPost;
	}

	@PostMapping("/deletePost")
	public List<Photo> deletePost(@RequestParam() Long postId, @RequestParam() List<Long> photoIdList) {
//		System.out.println(photoIdList);
		for (Long id : photoIdList) {
			photoRepository.deleteById(id);
		}
		photoPostRepository.deleteById(postId);

		return photoRepository.findAll().stream().sorted(Comparator.comparing(Photo::getId).reversed())
				.collect(Collectors.toList());
	}

	@PostMapping("/uploadReply")
	public List<PhotoReply> uploadReply(@RequestParam() String text, @RequestParam() Long postId,
			@RequestParam() Long userId) {
		User user = userRepository.findAllById(userId).get(0);
		PhotoPost photoPost = photoPostRepository.findAllById(postId).get(0);
		PhotoReply photoReply = new PhotoReply(user, text, photoPost);

		photoReplyRepository.save(photoReply);

		return photoReplyRepository.findAllByPhotoPostId(postId);
	}

	@PostMapping("/deleteReply")
	public List<PhotoReply> deleteReply(@RequestParam() Long id) {
		PhotoReply photoReply = photoReplyRepository.findAllById(id).get(0);
		photoReplyRepository.delete(photoReply);

		return photoReplyRepository.findAllByPhotoPostId(photoReply.getPhotoPost().getId());
	}

	@PostMapping("/clickHeart")
	public void cleakHeart(@RequestParam() Long postId, @RequestParam() Long userId) {
		PhotoHeart photoHeart = new PhotoHeart(userRepository.findAllById(userId).get(0),
				photoPostRepository.findAllById(postId).get(0));

		photoHeartRepository.save(photoHeart);
	}

	@PostMapping("/deleteHeart")
	public void deleteHeart(@RequestParam() Long postId, @RequestParam() Long userId) {
		PhotoHeart photoHeart = photoHeartRepository.findAllByPhotoPostIdAndUserId(postId, userId).get(0);

		photoHeartRepository.delete(photoHeart);
	}

	@GetMapping("/isFavorited/{postId}/{userId}")
	public boolean isFavorited(@PathVariable Long postId, @PathVariable Long userId) {
		if (photoHeartRepository.findAllByPhotoPostIdAndUserId(postId, userId).size() > 0) {
			return true;
		}
		return false;
	}

	@GetMapping("/getPostHearts/{postId}")
	public int getPostHearts(@PathVariable Long postId) {
		return photoHeartRepository.findAllByPhotoPostId(postId).size();
	}

	@GetMapping("/getReplyByPostId/{postId}")
	public List<PhotoReply> getReplyByPostId(@PathVariable Long postId) {
		return photoReplyRepository.findAllByPhotoPostId(postId);
	}
}
