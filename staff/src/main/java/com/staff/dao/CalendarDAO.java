package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.CalendarVO;
import com.staff.model.ProjectVO;

@Mapper
public interface CalendarDAO {
	
	public ArrayList<CalendarVO> getMbrCalendarList(String prj_no);

	public int updateCalendarInfo(CalendarVO uptCalendarVO);
	
	public int insertCalendarInfo(CalendarVO intCalendarVO);

	public int deleteCalendarInfo(CalendarVO detCalendarVO);
}
