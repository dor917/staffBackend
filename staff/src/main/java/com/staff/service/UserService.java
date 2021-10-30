package com.staff.service;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.staff.model.UserVO;

public interface UserService {
	public UserVO Login(UserVO userVO);
	public void logout(HttpSession session);
}
