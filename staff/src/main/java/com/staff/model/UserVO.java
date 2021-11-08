package com.staff.model;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserVO {
	private String no;
	private String id;
	private String password;
	private Date cre_date;


}
