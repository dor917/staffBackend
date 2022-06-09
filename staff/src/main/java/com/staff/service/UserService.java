package com.staff.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import com.staff.model.ProjectVO;
import com.staff.model.UserVO;


public interface UserService {
	public UserVO goLogin(UserVO userVO);
	public void logout(HttpSession session);
	public int updateMbrInfo(UserVO uptUserVO);
	public int insertMbrInfo(UserVO intUserVO);
	public int selectseq();
	public ArrayList<UserVO> selectMbrNm(String mbr_nm);

}
