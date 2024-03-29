package com.staff.service;

import java.util.ArrayList;

import com.staff.model.CalendarVO;


public interface CalendarService {
	
	ArrayList<CalendarVO> getPrjCalendarList(String prj_no);

	public CalendarVO getPrjIssueList(String issue_no);

	public int updateCalendarInfo(CalendarVO uptCalendarVO);

	public int insertCalendarInfo(CalendarVO intCalendarVO);

	public int deleteCalendarInfo(CalendarVO detCalendarVO);
}
