package com.staff.model;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarVO {
    private int prj_no;
    private int issue_type;
    private int mbr_no;
    private int issue_no;

    private String issue_tit;
    private String issue_cont;

    private Date issue_start_date;
    private Date issue_end_date;

}