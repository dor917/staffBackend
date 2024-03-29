package com.staff.service;

import java.util.ArrayList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.dao.ProjectSvnDAO;
import com.staff.model.ProjectSvnVO;

@Service
@MapperScan("com.staff.dao")
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
	
	@Override
	public String getRevisionNo() {
		return projectSvnDAO.getRevisionNo();
	}
	@Override
	public ArrayList<ProjectSvnVO> getProjectSvnInfoByName(ProjectSvnVO intProjectSvnVO) {
		// TODO Auto-generated method stub
		return projectSvnDAO.getProjectSvnInfoFileName(intProjectSvnVO);
	};
}




