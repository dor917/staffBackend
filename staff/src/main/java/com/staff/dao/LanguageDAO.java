package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.LanguageVO;

@Mapper
public interface LanguageDAO {


	public int insertLang(LanguageVO language);
	public ArrayList<LanguageVO> selectPrjLang(int prj_no);
}
