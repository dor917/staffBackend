package com.staff.service;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.staff.model.UserVO;


public interface UserService {
	public UserVO goLogin(UserVO userVO);
	public void logout(HttpSession session);
	public int updateMbrInfo(UserVO uptUserVO);
	public int insertMbrInfo(UserVO intUserVO);
	public int selectseq();
	public ArrayList<UserVO> selectMbrNm(String mbr_nm);
	public ArrayList<UserVO> getProjecMbrtList(String prj_no);

}
