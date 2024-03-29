package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.CalendarVO;

@Mapper
public interface CalendarDAO {
	
	public ArrayList<CalendarVO> getPrjCalendarList(String prj_no);

	public CalendarVO getPrjIssueList(String issue_no);
	
	public int updateCalendarInfo(CalendarVO uptCalendarVO);
	
	public int insertCalendarInfo(CalendarVO intCalendarVO);

	public int deleteCalendarInfo(CalendarVO detCalendarVO);
}
