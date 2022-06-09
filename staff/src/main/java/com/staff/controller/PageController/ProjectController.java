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
import org.springframework.web.servlet.view.RedirectView;

import com.staff.model.Language;
import com.staff.model.PrjMbrVO;
import com.staff.model.ProjectVO;
import com.staff.service.LanguageService;
import com.staff.service.LanguageServiceImpl;
import com.staff.service.ProjectServiceImpl;

@RestController
@MapperScan(basePackages = "com.staff.service")
public class ProjectController {
	@Autowired(required=true)
	ProjectServiceImpl projectService;
	
	@Autowired(required=true)
	LanguageServiceImpl languageService;

	@CrossOrigin
	@RequestMapping("/getMbrProjectList.staff")
	public ArrayList<ProjectVO> getMbrProjectList (HttpServletRequest req, HttpServletResponse res){
		ArrayList<ProjectVO> resultArr = new ArrayList<ProjectVO>();
		try {
			String mbr_no = req.getParameter("mbr_no");
			resultArr = projectService.getMbrProjectList(mbr_no);
			for (ProjectVO projectVO : resultArr) {
				projectVO.setLanguages(languageService.selectPrjLang(projectVO.getPrj_no()));
			}
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
		String prj_lang = req.getParameter("prj_lang");
		String[] prj_langs = prj_lang.split(",");
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
	public RedirectView insertProjectInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		// 1, 파라미터 받아오기
		int prj_no = projectService.selectseq();
		String mbr_no = req.getParameter("mbr_no");
		String prj_nm = req.getParameter("prj_nm");
		String prj_expl = req.getParameter("prj_expl");
		String prj_prog = req.getParameter("prj_prog");
		String prj_lan_nm = req.getParameter("prj_lan_nm");
		String prj_start_date = req.getParameter("prj_start_date");
		String prj_end_date = req.getParameter("prj_end_date");
		String prj_lang = req.getParameter("prj_lang");
		String[] prj_langs = prj_lang.split(",");
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
		ProjectVO intProjectVO = new ProjectVO();
		intProjectVO.setPrj_no(prj_no);
		intProjectVO.setPrj_nm(prj_nm);
		intProjectVO.setPrj_expl(prj_expl);
		intProjectVO.setPrj_prog(Integer.valueOf(prj_prog));
		intProjectVO.setPrj_lan_nm(prj_lan_nm);
		intProjectVO.setPrj_start_date(formatter.parse(prj_start_date));
		intProjectVO.setPrj_end_date(formatter.parse(prj_end_date));
		
		int result = projectService.insertProjectInfo(intProjectVO);
		if (result > 0) {
			PrjMbrVO intProjectMbrVO = new PrjMbrVO();
			int mbr_posi = 1;
			intProjectMbrVO.setMbr_no(Integer.valueOf(mbr_no));
			intProjectMbrVO.setPrj_no(prj_no);
			intProjectMbrVO.setMbr_posi(mbr_posi);
			int resultmbr = projectService.insertProjectMbrInfo(intProjectMbrVO);
			for (int i = 0; i < prj_langs.length; i++) {
				Language language = new Language();
				language.setPrj_no(prj_no);
				language.setLan_no(Integer.valueOf(prj_langs[i]));
				languageService.insertLang(language);
			}
		} // 돈하한테 피드백
		return new RedirectView("http://localhost:3000/Main"); 
		
	}
	
	@RequestMapping("/insertProjectMbrInfo.staff")
	public RedirectView insertProjectMbrInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String mbr_no = req.getParameter("mbr_no");
		String prj_no = req.getParameter("prj_no");
		String mbr_posi = req.getParameter("mbr_posi");
		
		
		System.out.println(mbr_no);
		System.out.println(prj_no);
		System.out.println(mbr_posi);
		
		PrjMbrVO intProjectMbrVO = new PrjMbrVO();
		intProjectMbrVO.setMbr_no(Integer.valueOf(mbr_no));
		intProjectMbrVO.setPrj_no(Integer.valueOf(prj_no));
		intProjectMbrVO.setMbr_posi(Integer.valueOf(mbr_posi));
		
		projectService.insertProjectMbrInfo(intProjectMbrVO);
		return new RedirectView("http://localhost:3000/Main");
		
	}
	
	@RequestMapping("/deleteProjectMbrInfo.staff")
	public void deleteProjectInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String mbr_no = req.getParameter("mbr_no");
		String prj_no = req.getParameter("prj_no");
		
		PrjMbrVO detProjectMbrVO = new PrjMbrVO();
		detProjectMbrVO.setMbr_no(Integer.valueOf(mbr_no));
		detProjectMbrVO.setPrj_no(Integer.valueOf(prj_no));
		
		projectService.deleteProjectMbrInfo(detProjectMbrVO);
		
	}
}	
