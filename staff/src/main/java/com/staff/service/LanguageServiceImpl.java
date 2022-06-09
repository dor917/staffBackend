package com.staff.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.CalendarDAO;
import com.staff.dao.LanguageDAO;
import com.staff.model.Language;

@Service
public class LanguageServiceImpl implements LanguageService{
	
	@Autowired
	LanguageDAO LanguageDAO;
	
	@Override
	public int insertLang(Language language) {
		return LanguageDAO.insertLang(language);
	}
	@Override
	public ArrayList<Language> selectPrjLang(int prj_no) {
		return LanguageDAO.selectPrjLang(prj_no);
	}
}
