package com.staff.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ChatListVO {
	private String date;
	private ArrayList<ChatVO> chatList;
}
