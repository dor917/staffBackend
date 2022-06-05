package com.staff.controller.PageController;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParser;
import com.jcraft.jsch.JSch;
import com.staff.util.ListeningThread;
import com.staff.util.SFTPUtil;
import com.staff.util.WritingThread;

@RestController
public class ChatContaller {
	
	Socket socket = null;
	@RequestMapping("/sendChat.staff")
	public String chatHistory(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String ip = "3.36.120.248";
	        int port = 1213;
	        String prj_no = req.getParameter("prj_no");
			String mbr_email = req.getParameter("mbr_email");
			String message = req.getParameter("message");
		
	        String sendMessage = prj_no + "!@##@!" + mbr_email +"!@##@!" + message;
	        
	        System.out.println(sendMessage);
	        connChatServer(ip, port, sendMessage);
	        return "success";
		} catch (Exception e) {
			return "fali";
		}
		
		
	}
	public void connChatServer(String ip, int port, String message) {
		Socket socket = null;
		PrintWriter pw = null;
		try {
			// 서버에 요청 보내기
			socket = new Socket(ip, port);
			System.out.println(socket.getInetAddress().getHostAddress() + "에 연결됨");
			
			// 메시지 받기
			pw = new PrintWriter(socket.getOutputStream());
			System.out.println(message);
			// 메세지 전달
			pw.println(message);
			pw.flush();
			
		} catch (IOException e) {
		    System.out.println(e.getMessage());
		} finally {
		    // 소켓 닫기 (연결 끊기)
		    try {
		    	if(socket != null) { socket.close(); }
				if(pw != null) { pw.close(); }
		    } catch (IOException e) {
		        System.out.println(e.getMessage());
		    }
		}
	}
}
