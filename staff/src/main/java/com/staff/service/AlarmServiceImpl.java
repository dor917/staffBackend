package com.staff.service;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.AlarmDAO;
import com.staff.model.AlarmVO;

@Service
@MapperScan("com.staff.dao")
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	AlarmDAO AlarmDAO;
	
	@Override
	public ArrayList<AlarmVO> getPrjAlarmList(String alarm_no) {
		return AlarmDAO.getPrjAlarmList(alarm_no);
	}
	
	@Override
	public int insertAlarmInfo(AlarmVO intAlarmVO) {
	return AlarmDAO.insertAlarmInfo(intAlarmVO);
	}
	
	@Override
	public int deleteAlarmInfo(AlarmVO detAlarmVO) {
		return AlarmDAO.deleteAlarmInfo(detAlarmVO);
	}
}