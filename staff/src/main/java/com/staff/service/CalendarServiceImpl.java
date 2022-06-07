package com.staff.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.CalendarDAO;
import com.staff.model.CalendarVO;
import com.staff.model.ProjectVO;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	CalendarDAO CalendarDAO;
	
	@Override
	public ArrayList<CalendarVO> getMbrCalendarList(String prj_no) {
		return CalendarDAO.getMbrCalendarList(prj_no);
	}
	
	@Override
	public int updateCalendarInfo(CalendarVO uptCalendarVO) {
		return CalendarDAO.updateCalendarInfo(uptCalendarVO);
	}

	@Override
	public int insertCalendarInfo(CalendarVO intCalendarVO) {
		return CalendarDAO.updateCalendarInfo(intCalendarVO);
	}
	
	
}
