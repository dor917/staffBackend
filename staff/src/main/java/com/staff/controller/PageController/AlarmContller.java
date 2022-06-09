package com.staff.controller.PageController;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.staff.model.AlarmVO;
import com.staff.service.AlarmServiceImpl;

@RestController
@MapperScan(basePackages = "com.staff.service")
public class AlarmContller {
	
	@Autowired(required=true)
	AlarmServiceImpl alarmService;

	@RequestMapping("/getPrjAlarmList.staff")
	public ArrayList<AlarmVO> getPrjAlarmList (HttpServletRequest req, HttpServletResponse res){

		ArrayList<AlarmVO> resultArr = new ArrayList<AlarmVO>();
		try {
			String prj_no = req.getParameter("prj_no");
			resultArr = alarmService.getPrjAlarmList(prj_no);
			System.out.println(prj_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArr;
	
	}
 
	@RequestMapping("/insertAlarminfo.staff")
	public void insetAlarminfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception{
		String alarm_no = req.getParameter("alarm_no");
		String prj_no =  req.getParameter("prj_no");
		String alarm_cont =  req.getParameter("alarm_cont");
	
		AlarmVO intAlarmVO = new AlarmVO();
		intAlarmVO.setAlarm_no(Integer.valueOf(alarm_no));
		intAlarmVO.setPrj_no(Integer.valueOf(prj_no));
		intAlarmVO.setAlarm_cont(alarm_cont);
		
		alarmService.insertAlarminfo(intAlarmVO);
	}
	
	@RequestMapping("/deleteAlarmInfo.staff")
	public void deleteAlarmInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String alarm_no = req.getParameter("alarm_no");
		String prj_no = req.getParameter("prj_no");
		
		AlarmVO detAlarmVO = new AlarmVO();
		detAlarmVO.setAlarm_no(Integer.valueOf(alarm_no));
		detAlarmVO.setPrj_no(Integer.valueOf(prj_no));
		
		alarmService.deleteAlarmInfo(detAlarmVO);
	}
}
	