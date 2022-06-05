package com.staff.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.staff.model.ProjectVO;

@Mapper
public interface ProjectDAO {

	public ArrayList<ProjectVO> getMbrProjectList(String mbr_no);

	public int updateProjectInfo(ProjectVO uptProjectVO);
}
