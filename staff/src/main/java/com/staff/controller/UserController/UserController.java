package com.staff.controller.UserController;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staff.dao.UserDAO;
import com.staff.model.UserVO;


@RestController
@MapperScan(basePackages="com.staff.dao")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@GetMapping("/login")
	public UserVO Login() {
		UserVO userVO = new UserVO();
		userVO.setId("test@naver.com");
		userVO.setPassword("test123");
		return userDAO.Login(userVO);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Spring Boot";
	}
	
	
	
}
