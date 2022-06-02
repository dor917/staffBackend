package com.staff.controller.PageController;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.staff.model.ProjectVO;
import com.staff.model.UserVO;
import com.staff.service.ProjectServiceImpl;
import com.staff.service.UserServiceImpl;

@RestController
@MapperScan(basePackages = "com.staff.service")
public class ProjectController {
	@Autowired(required=true)
	ProjectServiceImpl projectService;

	@CrossOrigin
	@RequestMapping("/getMbrProjectList.staff")
	public ArrayList<ProjectVO> getMbrProjectList (HttpServletRequest req, HttpServletResponse res){

		ArrayList<ProjectVO> resultArr = new ArrayList<ProjectVO>();
		try {
			String mbr_no = req.getParameter("mbr_no");
			resultArr = projectService.getMbrProjectList(mbr_no);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArr;
		
		
	}
	

}
