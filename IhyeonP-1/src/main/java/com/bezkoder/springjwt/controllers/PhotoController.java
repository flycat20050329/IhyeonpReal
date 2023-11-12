package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.Image;
import com.bezkoder.springjwt.models.ImagePost;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ImagePostRepository;
import com.bezkoder.springjwt.repository.ImageRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/photo")
public class PhotoController {

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ImagePostRepository imagePostRepository;

	@GetMapping("/getImage")
	public List<Image> getImage() {
		return imageRepository.findAll().stream().sorted(Comparator.comparing(Image::getId).reversed())
				.collect(Collectors.toList());
//		return imageRepository.findAll();
	}

	@GetMapping("/getImagePost")
//	public List<Image> getImagePost(@RequestParam() Long postId) {
	public List<Image> getImagePost(@RequestParam(required = false) Long postId) {
//		return imageRepository.findAll();
		System.out.println(postId);
		return null;
//		return imageRepository.findByImagePostId(postId);
	}

	@PostMapping("/uploadImage")
	public void uploadImage(@RequestParam() List<MultipartFile> images, @RequestParam() Long postId)
			throws IOException {
		ImagePost imagePost = new ImagePost(postId);

		List<Image> imageList = new ArrayList<Image>();

		for (int i = 0; i < images.size(); i++) {
			Image image = new Image(images.get(i).getBytes(), imagePost, i + 1);

			imageRepository.save(image);
			imageList.add(image);
		}
	}

	@PostMapping("/uploadImagePost")
	public Long uploadImagePost(@RequestParam() Long id, @RequestParam() int heart, @RequestParam() String text) {
		User user = new User(id);

		ImagePost imagePost = new ImagePost(user, heart, text);

		imagePostRepository.save(imagePost);

		return imagePost.getId();
	}

}
