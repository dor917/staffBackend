package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.model.LanguageVO;

@Service
public interface LanguageService {
	public int insertLang(LanguageVO languageVO);

	public ArrayList<LanguageVO> selectPrjLang(int prj_no);
}
