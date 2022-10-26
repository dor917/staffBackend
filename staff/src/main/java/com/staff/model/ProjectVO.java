package com.staff.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class ProjectVO {

	private int prj_no;
	private int prj_prog;

	private String prj_nm;
	private String prj_expl;
	private String Prj_lan_nm;

	private Date prj_start_date;
	private Date prj_end_date;
	private Date sys_reg_date;

//	PRJ_FILE_BASE
	private String file_url;
	private Date file_sys_reg_date;

//	PRJ_CHAT_BASE
	private String chat_url;
	private Date chat_sys_reg_date;
	
	private ArrayList<LanguageVO> languages;

}
