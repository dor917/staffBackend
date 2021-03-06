package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.AlarmVO;
import com.staff.model.PrjMbrVO;

@Mapper
public interface AlarmDAO {
	public ArrayList<AlarmVO> getPrjAlarmList(String prj_no);
	public int insertAlarminfo(AlarmVO intAlarmVO);
	public int deleteAlarmInfo(AlarmVO detAlarmVO);


}
