package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.model.CalendarVO;
import com.staff.model.ProjectVO;

@Service
public interface CalendarService {
	
	ArrayList<CalendarVO> getMbrCalendarList(String prj_no);

	public int updateCalendarInfo(CalendarVO uptCalendarVO);

	public int insertCalendarInfo(CalendarVO intCalendarVO);

}
