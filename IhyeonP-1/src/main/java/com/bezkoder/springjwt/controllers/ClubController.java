package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.ClubPost;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ClubPostRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/club")
public class ClubController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClubPostRepository clubPostRepository;
	
	@PostMapping("uploadClubPost")
	public Long UploadClubPost(@RequestParam() Long id, @RequestParam() String text) {
		User user = userRepository.findAllById(id).get(0);
		
		ClubPost clubPost;

		if (text.equals("")) {
			clubPost = new ClubPost(user, null);
		} else {
			clubPost = new ClubPost(user, text);
		}
		
		clubPostRepository.save(clubPost);
		
		return clubPost.getId();
	}
}
