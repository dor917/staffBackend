package com.staff.model;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserVO {
	private int mbr_no;
	
	private String mbr_nm;
	private String mbr_email;
	private String mbr_pw;
	private String mbr_phone;
	private String mbr_cont;
	private String mbr_addr;
	private String mbr_web;
	private String mbr_twit;
	private String mbr_insta;
	private String mbr_face;
	
	private Date mbr_brd;
	private Date sys_reg_date;

}
