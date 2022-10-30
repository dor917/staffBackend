package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.UserVO;

@Mapper
public interface MemberDAO {

	public UserVO goLogin(UserVO userVO);

	public int updateMbrInfo(UserVO uptUserVO);
	
	public int insertMbrInfo(UserVO intUserVO);

	public ArrayList<UserVO> getMbrUserList(String mbr_no);

	public int selectSeq();

	public ArrayList<UserVO> selectMbrNm(String mbr_nm);

	public ArrayList<UserVO> getProjecMbrtList(String prj_no);
}
