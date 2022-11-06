package com.staff.controller.PageController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.staff.model.ChatListVO;
import com.staff.model.ChatVO;

@RestController
public class ChatController {
	final static String ID = "staff";
	final static String IP = "3.16.44.131";
	final static int SSHPORT = 22;
	final static int CHATPORT = 1213;
	final static String PW = "staff123";
	Socket socket = null;

	@RequestMapping("/sendChat.staff")
	public String chatHistory(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String prj_no = req.getParameter("prj_no");
			String mbr_email = req.getParameter("mbr_email");
			String message = req.getParameter("message");
			if (null == message && "".equals(message)) {
				message = "";
			}
			String sendMessage = prj_no + "!@##@!" + mbr_email + "!@##@!" + message;

			System.out.println(sendMessage);
			connChatServer(IP, CHATPORT, sendMessage);
			return "success";
		} catch (Exception e) {
			return "fali";
		}

	}

	@RequestMapping("/getChatHistory.staff")
	public ArrayList<ChatListVO> getChatHistory(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setHeader("Access-Control-Allow-Origin", "*"); // 허용대상 도메인
		ArrayList<ChatListVO> result = new ArrayList<>();

		Session session = null;
		JSch jSch = null;
		ArrayList<String> fileList = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat changeSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date chatDate;
		String readStr = "";
		String dateStr;
		try {
			// 1. ssh 연결 설정
			String prj_no = req.getParameter("prj_no");
			jSch = new JSch();
			session = jSch.getSession(ID, IP, SSHPORT);
			session.setPassword(PW);

			Properties properties = new Properties();
			properties.put("StrictHostKeyChecking", "no");
			session.setConfig(properties);

			// 2. ssh 연결
			session.connect();

			Channel channel = session.openChannel("sftp");
			ChannelSftp channelSftp = (ChannelSftp) channel;
			channelSftp.connect();

			// 3. 파일 읽기
			StringBuffer stringBuffer = new StringBuffer();
			String dir = "/staffChat/" + prj_no;
			channelSftp.cd(dir);

			Vector<ChannelSftp.LsEntry> files = channelSftp.ls(dir);

			for (ChannelSftp.LsEntry entry : files) {
				if (!entry.getFilename().equals(".") && !entry.getFilename().equals("..")
						&& entry.getFilename().indexOf(".") != 0) {
					fileList.add(entry.getFilename());
				}
			}
			Collections.sort(fileList);
			// 4. return 설정

			if (fileList.size() > 0) {
				Date date = simpleDateFormat.parse(fileList.get(0));
				dateStr = changeSimpleDateFormat.format(date);

				for (String file : fileList) {
					if (!dateStr.equals(file)) {
						date = simpleDateFormat.parse(file);
						dateStr = changeSimpleDateFormat.format(date);
					}
					ChatListVO chatListVo = new ChatListVO();
					ArrayList<ChatVO> reListList = new ArrayList<>();
					BufferedReader reader = new BufferedReader(new InputStreamReader(channelSftp.get(file)));
					while ((readStr = reader.readLine()) != null && !"".equals(readStr)) {
						String[] chatArr = readStr.split("!@##@!");
						ChatVO chatVo = new ChatVO();
						chatVo.setPrj_no(prj_no);
						chatVo.setMbr_email(chatArr[0]);
						chatVo.setMessage(chatArr[1]);
						reListList.add(chatVo);
					}
					chatListVo.setDate(dateStr);
					chatListVo.setChatList(reListList);
					result.add(chatListVo);

				}

			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void connChatServer(String ip, int port, String message) {
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
				if (socket != null) {
					socket.close();
				}
				if (pw != null) {
					pw.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
}
