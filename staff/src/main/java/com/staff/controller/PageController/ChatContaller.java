package com.staff.controller.PageController;

import java.net.Socket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.JSch;
import com.staff.util.SFTPUtil;

@RestController
public class ChatContaller {

	@GetMapping("/chatHistory.do")
	public String chatHistory() {
		SFTPUtil sftpUtil = new SFTPUtil();
		sftpUtil.init("18.188.194.167", "ec2-user", null, 22, "C:\\Users\\82109\\Desktop\\개발정보\\staff\\Staff.pem");
		String hostHome = System.getProperty("user.home");
		sftpUtil.download("/chattingHistory", "20211028", hostHome);
	
		return "aa";
	}
}
