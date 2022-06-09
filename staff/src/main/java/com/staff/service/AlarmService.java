package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.dao.AlarmDAO;
import com.staff.model.AlarmVO;
import com.staff.model.PrjMbrVO;


public interface AlarmService {

	public ArrayList<AlarmVO> getPrjAlarmList(String prj_no);
	public int insertAlarminfo(AlarmVO intAlarmVO);
	public int deleteAlarmInfo(AlarmVO detAlarmVO);
}
