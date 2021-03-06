package com.staff.controller.PageController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

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
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.staff.model.ChatListVo;
import com.staff.model.ChatVO;
import com.staff.util.ListeningThread;
import com.staff.util.SFTPUtil;
import com.staff.util.WritingThread;

@RestController
public class ChatContaller {
	final static String ID = "staff";
	final static String IP = "54.180.134.66";
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
	public ArrayList<ChatListVo> getChatHistory(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setHeader("Access-Control-Allow-Origin", "*"); // ???????????? ?????????
		ArrayList<ChatListVo> result = new ArrayList<>();

		Session session = null;
		JSch jSch = null;
		ArrayList<String> fileList = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat changeSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date chatDate;
		String readStr = "";
		String dateStr;
		try {
			// 1. ssh ?????? ??????
			String prj_no = req.getParameter("prj_no");
			jSch = new JSch();
			session = jSch.getSession(ID, IP, SSHPORT);
			session.setPassword(PW);

			Properties properties = new Properties();
			properties.put("StrictHostKeyChecking", "no");
			session.setConfig(properties);

			// 2. ssh ??????
			session.connect();

			Channel channel = session.openChannel("sftp");
			ChannelSftp channelSftp = (ChannelSftp) channel;
			channelSftp.connect();

			// 3. ?????? ??????
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
			// 4. return ??????

			if (fileList.size() > 0) {
				Date date = simpleDateFormat.parse(fileList.get(0));
				dateStr = changeSimpleDateFormat.format(date);

				for (String file : fileList) {
					if (!dateStr.equals(file)) {
						date = simpleDateFormat.parse(file);
						dateStr = changeSimpleDateFormat.format(date);
					}
					ChatListVo chatListVo = new ChatListVo();
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
			// ????????? ?????? ?????????
			socket = new Socket(ip, port);
			System.out.println(socket.getInetAddress().getHostAddress() + "??? ?????????");

			// ????????? ??????
			pw = new PrintWriter(socket.getOutputStream());
			System.out.println(message);
			// ????????? ??????
			pw.println(message);
			pw.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			// ?????? ?????? (?????? ??????)
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
