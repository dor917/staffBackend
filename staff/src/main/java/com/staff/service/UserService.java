package com.staff.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import com.staff.model.UserVO;


public interface UserService {
	public UserVO goLogin(UserVO userVO);
	public void logout(HttpSession session);
	public int updateMbrInfo(UserVO uptUserVO);
}
