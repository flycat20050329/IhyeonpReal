package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/schoolInfo")
public class SchoolInfoController {

	private SchoolInfo schoolInfo = new SchoolInfo();

//	@PostMapping("/getInfo")
//	public List<List<String>> getSchoolInfo() {
//		return this.schoolInfo.GetSchoolInfo();
//	}

}
