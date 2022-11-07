package com.staff.service;

import java.util.ArrayList;

import com.staff.model.LanguageVO;


public interface LanguageService {
	public ArrayList<LanguageVO> selectPrjLang(int prj_no);

	public int insertLang(LanguageVO intlanguageVO);
	
	public int updateLang(LanguageVO uptlanguageVO);
	
	public int deleteLang(LanguageVO detlanguageVO);
}
