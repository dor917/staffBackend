package com.staff.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.AlarmDAO;
import com.staff.model.AlarmVO;

@Service
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	AlarmDAO AlarmDAO;
	
	@Override
	public ArrayList<AlarmVO> getPrjAlarmList(String prj_no) {
		return AlarmDAO.getPrjAlarmList(prj_no);
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
