package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.model.CalendarVO;

@Service
public interface CalendarService {
	
	ArrayList<CalendarVO> getMbrCalendarList(String issue_no);

	public int updateCalendarInfo(CalendarVO uptCalendarVO);

	public int insertCalendarInfo(CalendarVO intCalendarVO);

	public int deleteCalendarInfo(CalendarVO detCalendarVO);
}
