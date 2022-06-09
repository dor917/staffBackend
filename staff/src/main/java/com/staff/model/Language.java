package com.staff.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Language {
	private int prj_no;
	private int lan_no;
	
	private String lan_nm;
	
	private Date sys_reg_date;
}
