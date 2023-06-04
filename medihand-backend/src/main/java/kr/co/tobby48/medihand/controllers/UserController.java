package kr.co.tobby48.medihand.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tobby48.medihand.models.User;
import kr.co.tobby48.medihand.payload.request.profile.ProfileRequest;
import kr.co.tobby48.medihand.security.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@GetMapping()
	public List<User> getUsers() {
		return userDetailsService.getUsers();
	}
	
	@GetMapping("/{username}")
	public User findUserByUserName(@PathVariable String username) {
		return userDetailsService.findUserByUserName(username);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateUser(@Valid @RequestBody ProfileRequest profileRequest) {
		User user = userDetailsService.updateUser(profileRequest);

		return ResponseEntity.ok(user);
	}
}
