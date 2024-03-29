package com.staff.service;

import java.util.ArrayList;

import com.staff.model.PrjMbrVO;
import com.staff.model.ProjectVO;

public interface ProjectSerivce {
	
	public ArrayList<ProjectVO> getMbrProjectList(String mbr_no);
	public int updateProjectInfo(ProjectVO uptProjectVO);
	public int insertProjectInfo(ProjectVO intProjectVO);
	public int deleteProjectInfo(ProjectVO detProjectVO);
	public int insertProjectMbrInfo(PrjMbrVO intProjectVO);
	public int deleteProjectMbrInfo(PrjMbrVO detProjectVO);
	public int selectseq();
	public ProjectVO getProjectInfo(String prj_no);
	public int deleteProjectMbrInfoByPrjNo(String prj_no);
}
