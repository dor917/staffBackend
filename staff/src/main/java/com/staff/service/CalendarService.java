package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.model.CalendarVO;

@Service
public interface CalendarService {
	
	ArrayList<CalendarVO> getPrjCalendarList(String prj_no);

	public int updateCalendarInfo(CalendarVO uptCalendarVO);

	public int insertCalendarInfo(CalendarVO intCalendarVO);

	public int deleteCalendarInfo(CalendarVO detCalendarVO);
}
