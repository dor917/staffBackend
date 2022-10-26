package com.staff.controller.PageController;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.staff.model.AlarmVO;
import com.staff.service.AlarmServiceImpl;

@RestController
@MapperScan(basePackages = "com.staff.service")
public class AlarmController {
	
	@Autowired(required=true)
	AlarmServiceImpl alarmService;

	@CrossOrigin
	@RequestMapping("/getPrjAlarmList.staff")
	public ArrayList<AlarmVO> getPrjAlarmList (HttpServletRequest req, HttpServletResponse res){

		ArrayList<AlarmVO> resultArr = new ArrayList<AlarmVO>();
		try {
			String alarm_no = req.getParameter("alarm_no");
			resultArr = alarmService.getPrjAlarmList(alarm_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArr;
	
	}
 
	@RequestMapping("/insertAlarmInfo.staff")
	public void insetAlarminfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception{
		String prj_no =  req.getParameter("prj_no");
		String alarm_cont =  req.getParameter("alarm_cont");
	
		AlarmVO intAlarmVO = new AlarmVO();
		intAlarmVO.setPrj_no(Integer.valueOf(prj_no));
		intAlarmVO.setAlarm_cont(alarm_cont);
		
		alarmService.insertAlarmInfo(intAlarmVO);
	}
	
	@RequestMapping("/deleteAlarmInfo.staff")
	public void deleteAlarmInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String alarm_no = req.getParameter("alarm_no");
		
		AlarmVO detAlarmVO = new AlarmVO();
		detAlarmVO.setAlarm_no(Integer.valueOf(alarm_no));
		
		alarmService.deleteAlarmInfo(detAlarmVO);
	}
}