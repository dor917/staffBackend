package com.staff.model;

import java.util.Date;

import lombok.Data;

@Data
public class AlarmVO {
	private int prj_no;
	private int alarm_no;
	private String alarm_cont;
	private Date sys_reg_date;
}
