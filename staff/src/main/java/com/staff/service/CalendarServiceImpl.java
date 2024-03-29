package com.staff.service;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.CalendarDAO;
import com.staff.model.CalendarVO;

@Service
@MapperScan("com.staff.dao")
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	CalendarDAO CalendarDAO;
	
	@Override
	public ArrayList<CalendarVO> getPrjCalendarList(String prj_no) {
		return CalendarDAO.getPrjCalendarList(prj_no);
	}
	
	@Override
	public CalendarVO getPrjIssueList(String issue_no) {
		return CalendarDAO.getPrjIssueList(issue_no);
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
