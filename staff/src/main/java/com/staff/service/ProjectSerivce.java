package com.staff.service;

import java.util.ArrayList;

import com.staff.model.PrjMbrVO;
import com.staff.model.ProjectVO;

public interface ProjectSerivce {
	
	public ArrayList<ProjectVO> getMbrProjectList(String mbr_no);
	public int updateProjectInfo(ProjectVO uptProjectVO);
	public int insertProjectInfo(ProjectVO intProjectVO);
	public int insertProjectMbrInfo(PrjMbrVO intProjectVO);
	public int deleteProjectMbrInfo(PrjMbrVO detProjectVO);
	public int selectseq();
}
