package com.staff.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatListVo {
	private String date;
	private ArrayList<ChatVO> chatList;
}
