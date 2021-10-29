package com.staff.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.staff.dao.UserDAO;
import com.staff.model.UserVO;

public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDAO;

	@Override
	public int Login(UserVO userVO) {
		
		return userDAO.Login(userVO);
	}
	
	
}
