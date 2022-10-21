package com.staff.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.CalendarDAO;
import com.staff.model.CalendarVO;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	CalendarDAO CalendarDAO;
	
	@Override
	public ArrayList<CalendarVO> getPrjCalendarList(String issue_no) {
		return CalendarDAO.getPrjCalendarList(issue_no);
	}
	
	@Override
	public int insertCalendarInfo(CalendarVO intCalendarVO) {
		return CalendarDAO.insertCalendarInfo(intCalendarVO);
	}
	
	@Override
	public int updateCalendarInfo(CalendarVO uptCalendarVO) {
		return CalendarDAO.updateCalendarInfo(uptCalendarVO);
	}

	@Override
	public int deleteCalendarInfo(CalendarVO detCalendarVO) {
		return CalendarDAO.deleteCalendarInfo(detCalendarVO);
	}	

}
