package com.staff.dao;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.UserVO;

@Mapper
public interface MemberDAO {

	public UserVO goLogin(UserVO userVO);

}
