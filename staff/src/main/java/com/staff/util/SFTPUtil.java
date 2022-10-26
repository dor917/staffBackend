package com.staff.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPUtil {

	private Session session = null;
	private Channel channel = null;
	private ChannelSftp channelSftp = null;

	/**
	 * 서버와 연결에 필요한 값들을 가져와 초기화 시킴
	 *
	 * @param host       서버 주소
	 * @param userName   접속에 사용될 아이디
	 * @param password   비밀번호
	 * @param port       포트번호
	 * @param privateKey 키
	 */
	public void init(String host, String userName, String password, int port, String privateKey) {

		JSch jSch = new JSch();
		try {
			if (privateKey != null) {// 키가 존재한다면
				jSch.addIdentity(privateKey);
			}
			session = jSch.getSession(userName, host, port);

			if (privateKey == null) {// 키가 없다면
				session.setPassword(password);
			}

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
		} catch (JSchException e) {
			e.printStackTrace();
		}

		channelSftp = (ChannelSftp) channel;

	}

	/**
	 * 하나의 폴더를 생성한다.
	 *
	 * @param dir       이동할 주소
	 * @param mkdirName 생상할 폴더명
	 */
	public void mkdir(String dir, String mkdirName) {

		try {
			channelSftp.cd(dir);
			channelSftp.mkdir(mkdirName);
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 하나의 파일을 업로드 한다.
	 *
	 * @param dir  저장시킬 주소(서버)
	 * @param file 저장할 파일
	 */
	public void upload(String dir, File file) {

		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			channelSftp.cd(dir);
			channelSftp.put(in, file.getName());
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 하나의 파일을 다운로드 한다.
	 *
	 * @param dir              저장할 경로(서버)
	 * @param downloadFileName 다운로드할 파일
	 * @param path             저장될 공간
	 */
	public void download(String dir, String downloadFileName, String path) {
		InputStream in = null;
		FileOutputStream out = null;
		File rootPath = new File(path + "\\STAFF" );
		File rootFile = new File(path + "\\STAFF\\chatHistory.txt" );
		if (rootPath.exists()) {
			if (!rootPath.isDirectory()) {
				rootPath.mkdir();
			}
		}  else {
			rootPath.mkdir();
		}
		
		try {
			channelSftp.cd(dir);
			in = channelSftp.get(downloadFileName);
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rootFile.createNewFile();
			out = new FileOutputStream(rootFile);
			int i;

			while ((i = in.read()) != -1) {
				out.write(i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 서버와의 연결을 끊는다.
	 */
	public void disconnection() {
		channelSftp.quit();
		session.disconnect();

	}

}