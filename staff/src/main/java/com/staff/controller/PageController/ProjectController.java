package com.staff.controller.PageController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.staff.model.PrjMbrVO;
import com.staff.model.ProjectVO;
import com.staff.service.ProjectServiceImpl;

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
		
		projectService.updateProjectInfo(uptProjectVO);
		
	}
	
	@RequestMapping("/insertProjectInfo.staff")
	public void insertProjectInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String mbr_no = req.getParameter("mbr_no");
		String prj_no = req.getParameter("prj_no");
		String mbr_posi = req.getParameter("mbr_posi");
		
		PrjMbrVO intProjectVO = new PrjMbrVO();
		intProjectVO.setMbr_no(Integer.valueOf(mbr_no));
		intProjectVO.setPrj_no(Integer.valueOf(prj_no));
		intProjectVO.setMbr_posi(Integer.valueOf(mbr_posi));
		
		projectService.insertProjectInfo(intProjectVO);
		
	}
	
	@RequestMapping("/deleteProjectInfo.staff")
	public void deleteProjectInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String mbr_no = req.getParameter("mbr_no");
		String prj_no = req.getParameter("prj_no");
		
		PrjMbrVO detProjectVO = new PrjMbrVO();
		detProjectVO.setMbr_no(Integer.valueOf(mbr_no));
		detProjectVO.setPrj_no(Integer.valueOf(prj_no));
		
		projectService.deleteProjectInfo(detProjectVO);
	}
}	
