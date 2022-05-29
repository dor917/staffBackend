package com.staff.controller.PageController;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.jcraft.jsch.JSch;
import com.staff.util.ListeningThread;
import com.staff.util.SFTPUtil;
import com.staff.util.WritingThread;

@RestController
public class ChatContaller {
	Socket socket = null;
	@GetMapping("/chatHistory.do")
	public String chatHistory() {
		SFTPUtil sftpUtil = new SFTPUtil();
		sftpUtil.init("18.188.194.167", "ec2-user", null, 22, "C:\\Users\\82109\\Desktop\\개발정보\\staff\\Staff.pem");
		String hostHome = System.getProperty("user.home");
		sftpUtil.download("/chattingHistory", "20211028", hostHome);
	
		return "aa";
	}
//	@PostMapping("/sendChat.do")
//	@ResponseBody
//	public ChatVO sendChat(@RequestBody Map<String, Map> param) {
//		ChatVO chatVO = new ChatVO();
//		try {
//			// OutputStream - 클라이언트에서 Server로 메세지 발송 
//            		// socket의 OutputStream 정보를 OutputStream out에 넣은 뒤
//			OutputStream out = socket.getOutputStream();
//			OutputStreamWriter outw = new OutputStreamWriter(out, "UTF-8");
//            		// PrintWriter에 위 OutputStream을 담아 사용
//			PrintWriter writer = new PrintWriter(outw, true);
//			writer.println(param.get("userId").get("userId") + " : " + param.get("message").get("message")); // 입력한 메세지 발송
//			chatVO.setUserId((String) param.get("userId").get("userId"));
//			chatVO.setMessage((String) param.get("message").get("message"));
//		} catch (Exception e) {
//			e.printStackTrace(); // 예외처리
//		}
//		
//		return chatVO;
//	}
//	@GetMapping("/Chat")
//	public String Chat() {
//		try {
//			socket = new Socket("18.188.194.167", 1213); 
//			System.out.println("서버에 접속 성공!"); // 접속 확인용
//			
//            		// 서버에서 보낸 메세지 읽는 Thread
//			ListeningThread t1 = new ListeningThread(socket);
//
//			t1.start(); // ListeningThread Start
//
//	
//		} catch (IOException e) {
//			e.printStackTrace(); // 예외처리
//		}
//		System.out.println("sfadsads");
//	
//		return "aa";
//	}
}
