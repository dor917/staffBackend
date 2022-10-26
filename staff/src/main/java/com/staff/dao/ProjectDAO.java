package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.PrjMbrVO;
import com.staff.model.ProjectVO;

@Mapper
public interface ProjectDAO {

	public ArrayList<ProjectVO> getMbrProjectList(String mbr_no);

	public int updateProjectInfo(ProjectVO uptProjectVO);

	public int insertProjectInfo(ProjectVO intProjectVO);
	
	public int deleteProjectInfo(ProjectVO detProjectVO);

	public int insertProjectMbrInfo(PrjMbrVO intProjectMbrVO);

	public int deleteProjectMbrInfo(PrjMbrVO detProjectMbrVO);
	
	public int selectSeq();

	public ProjectVO getProjectInfo(String prj_no);
}
