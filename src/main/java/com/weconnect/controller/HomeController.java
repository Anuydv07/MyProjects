package com.weconnect.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class HomeController {
	
	@GetMapping("/home0")
	public String homeControllerHandler() {
		return "this is home controller";
	}
	
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "this is home controller 2";
	}
	@GetMapping("/home2")
	public String homeControllerHandler3() {
		return "this is home controller 3";
	}

}
