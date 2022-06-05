package com.staff.controller.PageController;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
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
	
	@RequestMapping("/updateProjectInfo.staff")
	public void updateProjectInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		// 1, 파라미터 받아오기
		String prj_no = req.getParameter("prj_no");
		String prj_nm = req.getParameter("prj_nm");
		String prj_expl = req.getParameter("prj_expl");
		String prj_prog = req.getParameter("prj_prog");
		String prj_start_date = req.getParameter("prj_start_date");
		String prj_end_date = req.getParameter("prj_end_date");
		

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		ProjectVO uptProjectVO = new ProjectVO();
		uptProjectVO.setPrj_no(Integer.valueOf(prj_no));
		uptProjectVO.setPrj_nm(prj_nm);
		uptProjectVO.setPrj_expl(prj_expl);
		uptProjectVO.setPrj_prog(Integer.valueOf(prj_prog));
		uptProjectVO.setPrj_start_date(formatter.parse(prj_start_date));
		uptProjectVO.setPrj_end_date(formatter.parse(prj_end_date));
		

		int check = projectService.updateProjectInfo(uptProjectVO);
	

	}
}	
