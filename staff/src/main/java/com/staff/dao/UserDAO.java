package com.staff.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.staff.model.UserVO;


@Mapper
public interface UserDAO {
	public UserVO Login(UserVO userVO);
}
