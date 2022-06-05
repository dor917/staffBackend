package com.staff.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.ProjectDAO;
import com.staff.model.PrjMbrVO;
import com.staff.model.ProjectVO;

@Service
public class ProjectServiceImpl implements ProjectSerivce{

	@Autowired
	ProjectDAO projectDAO;
	
	@Override
	public ArrayList<ProjectVO> getMbrProjectList(String mbr_no) {
		return projectDAO.getMbrProjectList(mbr_no);
	}

	@Override
	public int updateProjectInfo(ProjectVO uptProjectVO) {
		return projectDAO.updateProjectInfo(uptProjectVO);
	}

	@Override
	public int insertProjectInfo(PrjMbrVO intProjectVO) {
		return projectDAO.insertProjectInfo(intProjectVO);
	}

	@Override
	public int deleteProjectInfo(PrjMbrVO detProjectVO) {
		return projectDAO.deleteProjectInfo(detProjectVO);
	}



}
