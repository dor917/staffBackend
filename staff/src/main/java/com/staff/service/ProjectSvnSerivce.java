package com.staff.service;

import java.util.ArrayList;

import com.staff.model.ProjectSvnVO;

public interface ProjectSvnSerivce {

	public ArrayList<ProjectSvnVO> getProjectSvnInfo(String prj_no);

	public int insertProjectSvnInfo(ProjectSvnVO intProjectSvnVO);

	public int updateProjectSvnInfo(ProjectSvnVO uptProjectSvnVO);

	public int deleteProjectSvnInfo(ProjectSvnVO detProjectSvnVO);
	
	public String getRevisionNo();
	
	public ArrayList<ProjectSvnVO> getProjectSvnInfoByName(ProjectSvnVO intProjectSvnVO);
}
