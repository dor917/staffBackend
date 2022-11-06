package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.model.AlarmVO;



public interface AlarmService {

	public ArrayList<AlarmVO> getPrjAlarmList(String alarm_no);
	public int insertAlarmInfo(AlarmVO intAlarmVO);
	public int deleteAlarmInfo(AlarmVO detAlarmVO);
}