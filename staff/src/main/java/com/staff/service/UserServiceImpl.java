package com.staff.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.MemberDAO;
import com.staff.model.UserVO;

@Service
@MapperScan("com.staff.dao")
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
	
	@Override
	public int insertMbrInfo(UserVO intUserVO) {
		return memberDAO.insertMbrInfo(intUserVO);
	}

	@Override
	public int selectseq() {
		return memberDAO.selectSeq();
	}

	@Override
	public ArrayList<UserVO> selectMbrNm(String mbr_nm) {
		return memberDAO.selectMbrNm(mbr_nm);
	}
	
	@Override
	public ArrayList<UserVO> getProjecMbrtList(String prj_no) {
		return memberDAO.getProjecMbrtList(prj_no);
	}
}
