package com.staff.service;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.MemberDAO;
import com.staff.model.UserVO;

@Service
@MapperScan(basePackages = "com.staff.dao")
public class UserServiceImpl implements UserService{
	@Autowired
	MemberDAO memberDAO;

	@Override
	public UserVO goLogin(UserVO userVO) {
		UserVO loginUser = memberDAO.goLogin(userVO);
		return loginUser;
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	@Override
	public int updateMbrInfo(UserVO uptUserVO) {
		return memberDAO.updateMbrInfo(uptUserVO);
	}
	
	
}
