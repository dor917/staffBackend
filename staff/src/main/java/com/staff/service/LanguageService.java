package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.model.Language;

@Service
public interface LanguageService {
	public int insertLang(Language language);

	public ArrayList<Language> selectPrjLang(int prj_no);
}
