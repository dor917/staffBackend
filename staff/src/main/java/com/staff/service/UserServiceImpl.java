package com.staff.service;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.UserDAO;
import com.staff.model.UserVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDAO;

	@Override
	public UserVO Login(UserVO userVO) {
		UserVO loginUser = userDAO.Login(userVO);
		return loginUser;
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	
}
