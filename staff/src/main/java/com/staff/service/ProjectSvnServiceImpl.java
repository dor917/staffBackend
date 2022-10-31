package com.staff.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.ProjectSvnDAO;
import com.staff.model.ProjectSvnVO;

@Service
public class ProjectSvnServiceImpl implements ProjectSvnSerivce{

	@Autowired
	ProjectSvnDAO projectSvnDAO;
	
	@Override
	public ArrayList<ProjectSvnVO> getProjectSvnInfo(String prj_no) {
		return projectSvnDAO.getProjectSvnInfo(prj_no);
	}
	
	@Override
	public int insertProjectSvnInfo(ProjectSvnVO intProjectSvnVO) {
		return projectSvnDAO.insertProjectSvnInfo(intProjectSvnVO);
	}
	
	@Override
	public int updateProjectSvnInfo(ProjectSvnVO uptProjectSvnVO) {
		return projectSvnDAO.updateProjectSvnInfo(uptProjectSvnVO);
	}
	
	@Override
	public int deleteProjectSvnInfo(ProjectSvnVO detProjectSvnVO) {
		return projectSvnDAO.deleteProjectSvnInfo(detProjectSvnVO);
	}
}




