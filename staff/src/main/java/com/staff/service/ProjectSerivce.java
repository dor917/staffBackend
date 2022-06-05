package com.staff.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.staff.model.ProjectVO;



public interface ProjectSerivce {
	
	public ArrayList<ProjectVO> getMbrProjectList(String mbr_no);
	public int updateProjectInfo(ProjectVO uptProjectVO);
}
