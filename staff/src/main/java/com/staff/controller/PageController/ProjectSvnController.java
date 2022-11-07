package com.staff.controller.PageController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import com.groupdocs.comparison.Comparer;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.staff.model.ProjectSvnVO;
import com.staff.service.ProjectSvnServiceImpl;

@RestController
public class ProjectSvnController {

	@Autowired(required = true)
	ProjectSvnServiceImpl projectSvnService;

	final static String SVN_FILE_PATH = "/home/staff/staffFileList";
	final static String ID = "staff";
	final static String IP = "3.16.44.131";
	final static int SSHPORT = 22;
	final static int CHATPORT = 1213;
	final static String PW = "staff123";

	static Session session = null;
	static Channel channel = null;
	static ChannelSftp channelSftp = null;
	static FileInputStream in = null;

	@CrossOrigin
	@RequestMapping("/uploadPrjFileList.staff")
	public RedirectView uploadPrjFileList(@RequestParam("uploadFiles") MultipartFile[] uploadFiles,
			HttpServletRequest req, HttpServletResponse res) {
		try {
			JSch jsch = new JSch();

			String prj_no = req.getParameter("prj_no");
			String filePath = req.getParameter("filePath");
			String commitMessage = req.getParameter("commitMessage");
			String revNo = projectSvnService.getRevisionNo();

			session = jsch.getSession(ID, IP, SSHPORT);
			session.setPassword(PW);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;

			String destinyPath = SVN_FILE_PATH + "/" + prj_no + "/" + revNo + "/" + filePath;
			String tempSNVPath = prj_no + "/" + revNo + "/" + filePath;
			String[] filePaths = tempSNVPath.split("/");
			String tempPaths = SVN_FILE_PATH + "/";
			for (String string : filePaths) {
				mkdir(tempPaths, string);
				tempPaths += string + "/";
			}

			channelSftp.cd(destinyPath);
			String fileListName = "";
			for (MultipartFile multipartFile : uploadFiles) {
				File file = convert(multipartFile);
				in = new FileInputStream(file);
				channelSftp.put(in, multipartFile.getOriginalFilename());
				fileListName += multipartFile.getOriginalFilename() + ", ";
			}
			ProjectSvnVO preProjectSvnVO = new ProjectSvnVO();
			preProjectSvnVO.setRev_comment(commitMessage);
			preProjectSvnVO.setPrj_no(Integer.valueOf(prj_no));
			preProjectSvnVO.setRev_file_name(fileListName.substring(0, fileListName.length() - 2));
			preProjectSvnVO.setRevision_no(Integer.valueOf(revNo));
			projectSvnService.insertProjectSvnInfo(preProjectSvnVO);

		} catch (JSchException e) {
			System.out.println("JSchException" + e);
		} catch (SftpException e1) {
			System.out.println("SftpException" + e1);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (channelSftp != null)
				channelSftp.disconnect();
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();
		}
		return new RedirectView("http://localhost:3000/FileList");
	}

	@CrossOrigin
	@RequestMapping("/getProjectSvnInfo.staff")
	public ArrayList<ProjectSvnVO> getProjectSvnInfo(HttpServletRequest req, HttpServletResponse res) {

		String prj_no = req.getParameter("prj_no");

		ArrayList<ProjectSvnVO> resultArr = new ArrayList<ProjectSvnVO>();
		try {
			resultArr = projectSvnService.getProjectSvnInfo(prj_no);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArr;
	}
	
	@CrossOrigin
	@RequestMapping("/getProjectSvnInfoByName.staff")
	public ArrayList<ProjectSvnVO> getProjectSvnInfoByName(HttpServletRequest req, HttpServletResponse res) {

		String prj_no = req.getParameter("prj_no");
		String rev_file_name = req.getParameter("rev_file_name").trim();

		ArrayList<ProjectSvnVO> resultArr = new ArrayList<ProjectSvnVO>();
		try {
			ProjectSvnVO projectSvnVO = new ProjectSvnVO();
			projectSvnVO.setPrj_no(Integer.valueOf(prj_no));
			projectSvnVO.setRev_file_name(rev_file_name);
			resultArr = projectSvnService.getProjectSvnInfoByName(projectSvnVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArr;
	}
	
	
	@CrossOrigin
	@RequestMapping("/getProjectSvnFileInfo.staff")
	public Set<String> getProjectSvnFileInfo (HttpServletRequest req, HttpServletResponse res) {

		String prj_no = req.getParameter("prj_no");

		ArrayList<ProjectSvnVO> resultArr = new ArrayList<ProjectSvnVO>();
		ArrayList<String> resultSet =  new ArrayList<String>();
		try {
			resultArr = projectSvnService.getProjectSvnInfo(prj_no);

			for (ProjectSvnVO projectSvnVO : resultArr) {
				String[] tempArr = projectSvnVO.getRev_file_name().split(",");
				for (String string : tempArr) {
					resultSet.add(string);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 // 배열을 HashSet으로 변환
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(resultSet.toArray(new String[resultSet.size()])));
		return hashSet;
	}

	@RequestMapping("/insertProjectSvnInfo.staff")
	public void insertProjectSvnInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res)
			throws Exception {
		String prj_no = req.getParameter("prj_no");
		String rev_comment = req.getParameter("rev_comment");

		ProjectSvnVO intProjectSvnVO = new ProjectSvnVO();
		intProjectSvnVO.setPrj_no(Integer.valueOf(prj_no));
		intProjectSvnVO.setRev_comment(rev_comment);

		projectSvnService.insertProjectSvnInfo(intProjectSvnVO);
	}

	@RequestMapping("/updateProjectSvnInfo.staff")
	public void updateProjectSvnInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res)
			throws Exception {
		String revision_no = req.getParameter("revision_no");
		String prj_no = req.getParameter("prj_no");
		String rev_comment = req.getParameter("rev_comment");

		ProjectSvnVO uptProjectSvnVO = new ProjectSvnVO();
		uptProjectSvnVO.setRevision_no(Integer.valueOf(revision_no));
		uptProjectSvnVO.setPrj_no(Integer.valueOf(prj_no));
		uptProjectSvnVO.setRev_comment(rev_comment);

		projectSvnService.updateProjectSvnInfo(uptProjectSvnVO);
	}

	@RequestMapping("/deleteProjectSvnInfo.staff")
	public void deleteProjectSvnInfo(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res)
			throws Exception {
		String revision_no = req.getParameter("revision_no");

		ProjectSvnVO detProjectSvnVO = new ProjectSvnVO();
		detProjectSvnVO.setRevision_no(Integer.valueOf(revision_no));

		projectSvnService.deleteProjectSvnInfo(detProjectSvnVO);
	}

	@RequestMapping("/comparerSvnFile.staff")
	public void comparerSvnFile(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException  {
		Comparer comparer = new Comparer(new FileInputStream("C:\\staff\\" + "100" + "\\compare\\test.txt"));;
		try {
			// pdf 파일 생성
			createPDF("100", "156", "/test", "test.txt");
			createPDF("100", "159", "/test", "test.txt");

			// 비교
			comparer.add(new FileInputStream("C:\\staff\\" + "100" + "\\compare\\test2.txt"));
			comparer.compare("C:\\staff\\" + "100" + "\\compare\\test3.txt");
			
			File file = new File("C:\\staff\\" + "100" + "\\compare\\result.pdf");
			JSch jsch = new JSch();

			String prj_no = req.getParameter("prj_no");
			String filePath = req.getParameter("filePath");
			String commitMessage = req.getParameter("commitMessage");
			String revNo = projectSvnService.getRevisionNo();

			session = jsch.getSession(ID, IP, SSHPORT);
			session.setPassword(PW);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;

			String destinyPath = SVN_FILE_PATH + "/" + prj_no + "/" + revNo + "/" + filePath;
			channelSftp.cd("/home/staff/staffFileList/100/comparer");
			in = new FileInputStream(file);
			channelSftp.put(in, file.getName());
		


		} catch (JSchException e) {
			System.out.println("JSchException" + e);
		} catch (SftpException e1) {
			System.out.println("SftpException" + e1);
		} catch (Exception e) {
			System.out.println(e);
			comparer.dispose();
		} finally {
			if (channelSftp != null)
				channelSftp.disconnect();
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();
		}
	}

	public void createPDF(String prj_no, String revName, String realFilePath, String fileName) {
		String pdfFileName = revName + fileName.substring(0, fileName.lastIndexOf('.')) + ".pdf";
		String dir = "/home/staff/staffFileList/" + prj_no + "/comparer";
		String pdfDir = "C:\\staff\\" + prj_no + "\\compare\\";

		try {
			File pdfDirFiel = new File(pdfDir);
			if (!pdfDirFiel.exists())
				pdfDirFiel.mkdirs(); // 파일경로 없으면 생성

			JSch jsch = new JSch();
			session = jsch.getSession(ID, IP, SSHPORT);
			session.setPassword(PW);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			System.out.println("/home/staff/staffFileList/" + prj_no + "/" + revName + realFilePath);
			channelSftp.cd("/home/staff/staffFileList/" + prj_no + "/" + revName + realFilePath);

			InputStream stream = channelSftp.get(fileName);
			Document document = new Document();
			Page page = document.getPages().add();

	
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(stream));
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				page.getParagraphs().add(new TextFragment(line + "\n"));
			}

			bufReader.close();

			if (!exists("/home/staff/staffFileList/" + prj_no + "/" + "comparer")) {
				mkdir("/home/staff/staffFileList/" + prj_no + "/", "comparer");
			}

			document.save(pdfDir + "\\" + pdfFileName);
			document.close();

		} catch (JSchException e) {
			System.out.println("JSchException" + e);
		} catch (SftpException e1) {
			System.out.println("SftpException" + e1);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (channelSftp != null)
				channelSftp.disconnect();
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();
		}
	}

	public File convert(MultipartFile file) throws IllegalStateException, IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	/**
	 * 디렉토리 생성
	 *
	 * @param dir       이동할 주소
	 * @param mkdirName 생성할 디렉토리명
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
	 * 디렉토리( or 파일) 존재 여부
	 * 
	 * @param path 디렉토리 (or 파일)
	 * @return
	 */
	public boolean exists(String path) {
		Vector res = null;
		try {
			res = channelSftp.ls(path);
		} catch (SftpException e) {
			if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
				return false;
			}
		}
		return res != null && !res.isEmpty();
	}
}