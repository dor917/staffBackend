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

import com.staff.model.ProjectSvnVO;
import com.staff.service.ProjectSvnServiceImpl;

@RestController
@MapperScan(basePackages = "com.staff.service")
public class ProjectSvnController {
	@Autowired(required = true)
	ProjectSvnServiceImpl projectSvnService;
	
	@CrossOrigin
	@RequestMapping("/getProjectSvnInfo.staff")
	public ArrayList<ProjectSvnVO> getProjectSvnInfo(HttpServletRequest req, HttpServletResponse res) {

		String prj_no = req.getParameter("prj_no");
		
		ArrayList<ProjectSvnVO> resultArr = new ArrayList<ProjectSvnVO>();
		try {
			resultArr = projectSvnService.getProjectSvnInfo(prj_no);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArr;
	}

	@RequestMapping("/insertProjectSvnInfo.staff")
	public void insertProjectSvnInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String prj_no = req.getParameter("prj_no");
		String rev_comment = req.getParameter("rev_comment");
		
		ProjectSvnVO intProjectSvnVO = new ProjectSvnVO();
		intProjectSvnVO.setPrj_no(Integer.valueOf(prj_no));
		intProjectSvnVO.setRev_comment(rev_comment);
		
		projectSvnService.insertProjectSvnInfo(intProjectSvnVO);
	}
	
	@RequestMapping("/updateProjectSvnInfo.staff")
	public void updateProjectSvnInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String revision_no = req.getParameter("revision_no");
		String prj_no = req.getParameter("prj_no");
		String rev_comment = req.getParameter("rev_comment");
		
		ProjectSvnVO uptProjectSvnVO = new ProjectSvnVO();
		uptProjectSvnVO.setRevision_no(Integer.valueOf(revision_no));
		uptProjectSvnVO.setPrj_no(Integer.valueOf(prj_no));
		uptProjectSvnVO.setRev_comment(rev_comment);
		
		projectSvnService.updateProjectSvnInfo(uptProjectSvnVO);
	}
	
	@RequestMapping("/deleteProjectSvnInfo.staff")
	public void deleteProjectSvnInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String revision_no = req.getParameter("revision_no");
		
		ProjectSvnVO detProjectSvnVO = new ProjectSvnVO();
		detProjectSvnVO.setRevision_no(Integer.valueOf(revision_no));
		
		projectSvnService.deleteProjectSvnInfo(detProjectSvnVO);
	}
	
	
}