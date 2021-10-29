package com.staff.dao;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.UserVO;

@Mapper
public interface UserDAO {
	public int Login(UserVO userVO);
}
