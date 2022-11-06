package com.staff.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ProjectSvnVO {
	public int revision_no;
	public int prj_no;
	
	public String rev_file_name;
	public String rev_comment;
	public Date sys_reg_date;
	public String sys_reg_date_str;
}
