package com.staff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/hello")
	public String hello() {
		return "Spring Boot";
	}
	@GetMapping("/Login")
	public String Login() {
		return "xzczxczx Boot";
	}
}
