package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.LanguageVO;

@Mapper
public interface LanguageDAO {

	public ArrayList<LanguageVO> selectPrjLang(int prj_no);

	public int insertLang(LanguageVO intlanguageVO);
	
	public int updateLang(LanguageVO uptlanguageVO);
	
	public int deleteLang(LanguageVO detlanguageVO);
}
