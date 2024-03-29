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
import org.springframework.web.servlet.view.RedirectView;

import com.staff.model.LanguageVO;
import com.staff.model.PrjMbrVO;
import com.staff.model.ProjectVO;
import com.staff.service.LanguageServiceImpl;
import com.staff.service.ProjectServiceImpl;
import com.staff.service.UserServiceImpl;

@RestController
public class ProjectController {
	@Autowired(required=true)
	ProjectServiceImpl projectService;
	
	@Autowired(required=true)
	LanguageServiceImpl languageService;
	
	@Autowired(required=true)
	UserServiceImpl userServiceImpl;

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
	
	@CrossOrigin
	@RequestMapping("/getProjectInfo.staff")
	public ProjectVO getProjectInfo (HttpServletRequest req, HttpServletResponse res){
		ProjectVO projectVO = null;
		try {
			String prj_no = req.getParameter("prj_no");
			projectVO = projectService.getProjectInfo(prj_no);
			projectVO.setLanguages(languageService.selectPrjLang(Integer.valueOf(prj_no)));
			projectVO.setUserVOs(userServiceImpl.getProjecMbrtList(prj_no));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projectVO;
	}
	
	
	@RequestMapping("/updateProjectInfo.staff")
	public RedirectView updateProjectInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		// 1, 파라미터 받아오기
		String prj_no = req.getParameter("prj_no");
		String prj_nm = req.getParameter("prj_nm");
		String prj_expl = req.getParameter("prj_expl");
		String prj_prog = req.getParameter("prj_prog");
		String prj_start_date = req.getParameter("prj_start_date");
		String prj_end_date = req.getParameter("prj_end_date");
		String prj_lang = req.getParameter("prj_lang");
		String strMbr_no = req.getParameter("mbr_nos");
	
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		ProjectVO uptProjectVO = new ProjectVO();
		uptProjectVO.setPrj_no(Integer.valueOf(prj_no));
		uptProjectVO.setPrj_nm(prj_nm);
		uptProjectVO.setPrj_expl(prj_expl);
		uptProjectVO.setPrj_prog(Integer.valueOf(prj_prog));
		uptProjectVO.setPrj_start_date(formatter.parse(prj_start_date));
		uptProjectVO.setPrj_end_date(formatter.parse(prj_end_date));
		
		projectService.updateProjectInfo(uptProjectVO);
		if (null != strMbr_no && !"".equals(strMbr_no)) {
			projectService.deleteProjectMbrInfoByPrjNo(prj_no);
			String[] mbr_nos = strMbr_no.split(",");
			int mbr_posi = 1;
			for (int i = 0; i < mbr_nos.length; i++) {
				PrjMbrVO proMbrVO = new PrjMbrVO();
				proMbrVO.setMbr_no(Integer.valueOf(mbr_nos[i]));
				proMbrVO.setPrj_no(Integer.valueOf(prj_no));
				proMbrVO.setMbr_posi(mbr_posi);
				projectService.insertProjectMbrInfo(proMbrVO);
			}
		}
		
		if (null != prj_lang && !"".equals(prj_lang)) {
			String[] prj_langs = prj_lang.split(",");
			//프로젝트 번호로 언어 삭제하는거 호출 languageService.메서드 (prj_no) ? valueOf(prj_no)
			LanguageVO detLanguageVO = new LanguageVO();
			detLanguageVO.setPrj_no(Integer.valueOf(prj_no));
			
			languageService.deleteLang(detLanguageVO);
			
			//언어 인서트 호출
			for (int i = 0; i < prj_langs.length; i++) {
				LanguageVO intlanguageVO = new LanguageVO();
				intlanguageVO.setPrj_no(Integer.valueOf(prj_no));
				intlanguageVO.setLan_no(Integer.valueOf(prj_langs[i]));
				languageService.insertLang(intlanguageVO);
			}
		}
		
		return new RedirectView("http://localhost:3000/Project");
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
		String[] mbr_nos = req.getParameter("mbr_nos").split(",");
		
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
			int mbr_posi = 1;
			for (int i = 0; i < mbr_nos.length; i++) {
				PrjMbrVO proMbrVO = new PrjMbrVO();
				proMbrVO.setMbr_no(Integer.valueOf(mbr_nos[i]));
				proMbrVO.setPrj_no(prj_no);
				proMbrVO.setMbr_posi(mbr_posi);
				projectService.insertProjectMbrInfo(proMbrVO);
			}
			for (int i = 0; i < prj_langs.length; i++) {
				LanguageVO intlanguageVO = new LanguageVO();
				intlanguageVO.setPrj_no(prj_no);
				intlanguageVO.setLan_no(Integer.valueOf(prj_langs[i]));
				languageService.insertLang(intlanguageVO);
			}
		} // 돈하한테 피드백
		return new RedirectView("http://localhost:3000/Main");
		
	}
	
	@RequestMapping("/deleteProjectInfo.staff")
	public RedirectView deleteProjectInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String prj_no = req.getParameter("prj_no");
		
		ProjectVO detProjectVO = new ProjectVO();
		detProjectVO.setPrj_no(Integer.valueOf(prj_no));
		
		projectService.deleteProjectInfo(detProjectVO);
		return new RedirectView("http://localhost:3000/Main");
	}
	
	@RequestMapping("/insertProjectMbrInfo.staff")
	public RedirectView insertProjectMbrInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String mbr_no = req.getParameter("mbr_no");
		String prj_no = req.getParameter("prj_no");
		String mbr_posi = req.getParameter("mbr_posi");
		
		PrjMbrVO intProjectMbrVO = new PrjMbrVO();
		intProjectMbrVO.setMbr_no(Integer.valueOf(mbr_no));
		intProjectMbrVO.setPrj_no(Integer.valueOf(prj_no));
		intProjectMbrVO.setMbr_posi(Integer.valueOf(mbr_posi));
		
		projectService.insertProjectMbrInfo(intProjectMbrVO);
		return new RedirectView("http://localhost:3000/Main");
		
	}
	
	@RequestMapping("/deleteProjectMbrInfo.staff")
	public void deleteProjectMbrInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		String mbr_no = req.getParameter("mbr_no");
		String prj_no = req.getParameter("prj_no");
		
		PrjMbrVO detProjectMbrVO = new PrjMbrVO();
		detProjectMbrVO.setMbr_no(Integer.valueOf(mbr_no));
		detProjectMbrVO.setPrj_no(Integer.valueOf(prj_no));
		
		projectService.deleteProjectMbrInfo(detProjectMbrVO);
		
	}
}	
