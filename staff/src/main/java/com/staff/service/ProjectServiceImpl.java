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
	public int insertProjectInfo(ProjectVO intProjectVO) {
		return projectDAO.insertProjectInfo(intProjectVO);
	}
	
	@Override
	public int deleteProjectInfo(ProjectVO detProjectVO) {
		return projectDAO.deleteProjectInfo(detProjectVO);
	}

	@Override
	public int insertProjectMbrInfo(PrjMbrVO intProjectMbrVO) {
		return projectDAO.insertProjectMbrInfo(intProjectMbrVO);
	}
	@Override
	public int deleteProjectMbrInfo(PrjMbrVO detProjectMbrVO) {
		return projectDAO.deleteProjectMbrInfo(detProjectMbrVO);
	}
	@Override
	public int selectseq() {
		return projectDAO.selectSeq();
	}
}




