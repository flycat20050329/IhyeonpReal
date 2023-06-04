package com.ykm.ihyeon.security;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	final private static Logger LOG = Logger.getGlobal();
	public static final String SECURED_TEXT = "Hello from the secured resource!";
	

	@GetMapping("/login")
	public void login() {
		LOG.info("GET successfully called on /login resource");
	}

	// 테스트용
	@GetMapping("/hello")
	public void hello() {
		System.out.println("HelloWorld");
	}
}
