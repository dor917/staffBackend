package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.ProjectSvnVO;

@Mapper
public interface ProjectSvnDAO {

	public ArrayList<ProjectSvnVO> getProjectSvnInfo(String prj_no);
	
	public int insertProjectSvnInfo(ProjectSvnVO intProjectSvnVO);
	
	public int updateProjectSvnInfo(ProjectSvnVO uptProjectSvnVO);
	
	public int deleteProjectSvnInfo(ProjectSvnVO detProjectSvnVO);
	
	public String getRevisionNo();
	
}