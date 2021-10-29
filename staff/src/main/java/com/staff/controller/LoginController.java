package com.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.staff.dao.UserDAO;
import com.staff.model.UserVO;

@RestController
public class LoginController {
	@Autowired
	UserDAO userDAO;
	
//	@GetMapping("/Login")
	public int Login() {
//		"test@naver.com", "test123"
		UserVO userVO = new UserVO();
		return userDAO.Login(userVO);
	}
	
//	public static void main(String[] args) {
//		UserVO userVO = new UserVO();
//		System.out.println("@");
//	}
}
