package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.AlarmVO;

@Mapper
public interface AlarmDAO {
	public ArrayList<AlarmVO> getAlramInfo(String mbr_no);

}
