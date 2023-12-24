package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/info")
public class InfoController {
	private Schedule schedule = new Schedule();
	private Lunch lunch = new Lunch();
	private SchoolInfo schoolInfo = new SchoolInfo();
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/getLunchInfo")
	public List<List<String>> getLunchInfo() {
		return this.lunch.getLunchInfo();
	}
	
	@GetMapping("/getScheduleInfo")
	public List<List<String>> getScheduleInfo(@RequestParam String name) {
		User u = userRepository.findAllByUsername(name).get(0);
		
		return this.schedule.getScheduleInfo(u);
	}
	
	@GetMapping("/getSchoolInfo")
	public List<String> getSchoolInfo(@RequestParam String loca, @RequestParam String name) {
		return this.schoolInfo.GetSchoolName(loca, name);
	}
}
