package com.staff.controller.PageController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.staff.model.CalendarVO;
import com.staff.service.CalendarServiceImpl;

@RestController
public class CalendarContaller {
	
	@Autowired(required = true)
	CalendarServiceImpl calendarService;
	
	@CrossOrigin
	@RequestMapping("/getPrjCalendarList.staff")
	public ArrayList<CalendarVO> getPrjCalendarList (HttpServletRequest req, HttpServletResponse res){

		ArrayList<CalendarVO> resultArr = new ArrayList<CalendarVO>();
		try {
			String issue_no = req.getParameter("issue_no");
			resultArr = calendarService.getPrjCalendarList(issue_no);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArr;
	}
	
	@RequestMapping("/insertCalendarInfo.staff")
	public void insertCalendarInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String prj_no = req.getParameter("prj_no");
		String issue_type = req.getParameter("issue_type");
		String issue_tit = req.getParameter("issue_tit");
		String issue_cont = req.getParameter("issue_cont");
		String mbr_no = req.getParameter("mbr_no");
		String issue_start_date = req.getParameter("issue_start_date");
		String issue_end_date = req.getParameter("issue_end_date");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		CalendarVO intCalendarVO = new CalendarVO();
		intCalendarVO.setPrj_no(Integer.valueOf(prj_no));
		intCalendarVO.setIssue_type(Integer.valueOf(issue_type));
		intCalendarVO.setIssue_tit(issue_tit);
		intCalendarVO.setIssue_cont(issue_cont);
		intCalendarVO.setMbr_no(Integer.valueOf(mbr_no));
		intCalendarVO.setIssue_start_date(formatter.parse(issue_start_date));
		intCalendarVO.setIssue_end_date(formatter.parse(issue_end_date));
		
		calendarService.insertCalendarInfo(intCalendarVO);
	}
	
	@RequestMapping("/updateCalendarInfo.staff")
	public void updateCalendarInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		// 1, 파라미터 받아오기
		String issue_no = req.getParameter("issue_no");
		String prj_no = req.getParameter("prj_no");
		String issue_type = req.getParameter("issue_type");
		String issue_tit = req.getParameter("issue_tit");
		String issue_cont = req.getParameter("issue_cont");
		String mbr_no = req.getParameter("mbr_no");
		String issue_start_date = req.getParameter("issue_start_date");
		String issue_end_date = req.getParameter("issue_end_date");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		CalendarVO uptCalendarVO = new CalendarVO();
		uptCalendarVO.setIssue_no(Integer.valueOf(issue_no));
		uptCalendarVO.setPrj_no(Integer.valueOf(prj_no));
		uptCalendarVO.setIssue_type(Integer.valueOf(issue_type));
		uptCalendarVO.setIssue_tit(issue_tit);
		uptCalendarVO.setIssue_cont(issue_cont);
		uptCalendarVO.setMbr_no(Integer.valueOf(mbr_no));
		uptCalendarVO.setIssue_start_date(formatter.parse(issue_start_date));
		uptCalendarVO.setIssue_end_date(formatter.parse(issue_end_date));

		calendarService.updateCalendarInfo(uptCalendarVO);
	}
	
	@RequestMapping("/deleteCalendarInfo.staff")
	public void deleteCalendarInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		// 1, 파라미터 받아오기
		
		String issue_no = req.getParameter("issue_no");
		
		CalendarVO detCalendarVO = new CalendarVO();
		detCalendarVO.setIssue_no(Integer.valueOf(issue_no));

		calendarService.deleteCalendarInfo(detCalendarVO);
	}
}
