package com.ykm.portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IhyeonPController {

//	@RequestMapping(value = "/")
//	public String home() {
//		return "";
//	}
	
	@GetMapping("/a")
	public String axiosTest(){
		return "axios 연결됨";
	}
}
