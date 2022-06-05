package com.staff.controller.PageController;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.staff.model.AlarmVO;
import com.staff.service.AlarmService;
import com.staff.service.AlarmServiceImpl;

@RestController
@MapperScan(basePackages = "com.staff.service")
public class AlarmContller {
	
	@Autowired(required=true)
	AlarmServiceImpl alarmService;

	@RequestMapping("/getPrjAlarmlist.staff")
	public ArrayList<AlarmVO> getMbrAlarmtList (HttpServletRequest req, HttpServletResponse res){

		ArrayList<AlarmVO> resultArr = new ArrayList<AlarmVO>();
		try {
			String prj_no = req.getParameter("prj_no");
			resultArr = alarmService.getPrjAlarmList(prj_no);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArr;
	
	}
	

}