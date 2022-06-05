package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.dao.AlarmDAO;
import com.staff.model.AlarmVO;


public interface AlarmService {

	public ArrayList<AlarmVO> getPrjAlarmList(String prj_no);

}
