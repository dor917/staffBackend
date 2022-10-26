package com.staff.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.staff.controller.PageController", "com.staff.service", "com.staff.dao"})
public class StaffApplication {
	public static void main(String[] args) {
		SpringApplication.run(StaffApplication.class, args);
		
	}


}
