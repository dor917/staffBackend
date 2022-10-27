package com.staff.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.LanguageDAO;
import com.staff.model.LanguageVO;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageDAO LanguageDAO;

	@Override
	public ArrayList<LanguageVO> selectPrjLang(int prj_no) {
		return LanguageDAO.selectPrjLang(prj_no);
	}

	@Override
	public int insertLang(LanguageVO intlanguageVO) {
		return LanguageDAO.insertLang(intlanguageVO);
	}

	@Override
	public int updateLang(LanguageVO uptlanguageVO) {
		return LanguageDAO.updateLang(uptlanguageVO);
	}

	@Override
	public int deleteLang(LanguageVO detlanguageVO) {
		return LanguageDAO.deleteLang(detlanguageVO);
	}
}
