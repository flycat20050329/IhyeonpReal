package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/schedule")
public class ScheduleControler {

	private Schedule schedule = new Schedule();
	
	@GetMapping("/bbbbb")
	public String hib() {
		return "bbbb 호출";
	}

	@GetMapping("/getInfo")
	public List<String>[] getLunchInfo() {
		return this.schedule.GetScheduleInfo();
	}

}
