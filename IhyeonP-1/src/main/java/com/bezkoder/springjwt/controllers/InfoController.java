package com.bezkoder.springjwt.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/info")
public class InfoController {
	private Schedule schedule = new Schedule();
	private Lunch lunch = new Lunch();
	private SchoolInfo schoolInfo = new SchoolInfo();
	
	@GetMapping("/getLunchInfo")
	public List<List<String>> getLunchInfo() {
		return this.lunch.getLunchInfo();
	}
	
	@GetMapping("/getScheduleInfo")
	public List<List<String>> getScheduleInfo() {
		return this.schedule.getScheduleInfo();
	}
	
	@PostMapping("/getSchoolInfo")
	public Map<String, String> getSchoolInfo() {
		return this.schoolInfo.GetSchoolInfo();
	}
}
