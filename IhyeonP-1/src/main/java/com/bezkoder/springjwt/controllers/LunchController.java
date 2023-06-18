package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.lunch.Lunch;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lunch")
public class LunchController {
	
	private Lunch lunch;
  
  @GetMapping("/getInfo")
  public List<List<String>> getLunchInfo(){
	  return lunch.getLunchInfo();
	  
  }
  

}
