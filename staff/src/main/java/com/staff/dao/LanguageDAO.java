package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.Language;

@Mapper
public interface LanguageDAO {


	public int insertLang(Language language);
	public ArrayList<Language> selectPrjLang(int prj_no);
}
