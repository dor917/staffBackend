package com.staff.controller.PageController;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.staff.model.UserVO;
import com.staff.service.UserService;
import com.staff.service.UserServiceImpl;

@RestController
@MapperScan(basePackages = "com.staff.service")
public class UserController {

	@Autowired(required = true)
	UserServiceImpl userService;

	@PostMapping("/gologin.staff")
	public RedirectView goLogin(UserVO userVO, HttpServletRequest req, RedirectAttributes rttr,
			HttpServletResponse res) {
		res.setContentType("application/json;charset=UTF-8");  
		res.setCharacterEncoding("UTF-8");  
		try {
			HttpSession session = req.getSession();

			UserVO loginUser = userService.goLogin(userVO);

			if (loginUser == null) {
				rttr.addFlashAttribute("msg", false);

			} else {	
				URLEncoder.encode(String.valueOf(loginUser.getMbr_no()), "utf-8");

				Cookie mbr_no = new Cookie("mbr_no", URLEncoder.encode(String.valueOf(loginUser.getMbr_no()), "UTF-8"));
				Cookie mbr_nm = new Cookie("mbr_nm", URLEncoder.encode(String.valueOf(loginUser.getMbr_nm()), "UTF-8"));
				Cookie mbr_email = new Cookie("mbr_email", URLEncoder.encode(String.valueOf(loginUser.getMbr_email()), "UTF-8"));
				Cookie mbr_pw = new Cookie("mbr_pw", URLEncoder.encode(String.valueOf(loginUser.getMbr_pw()), "UTF-8"));
				Cookie mbr_phone = new Cookie("mbr_phone", URLEncoder.encode(String.valueOf(loginUser.getMbr_phone()), "UTF-8"));
				Cookie mbr_cont = new Cookie("mbr_cont", URLEncoder.encode(String.valueOf(loginUser.getMbr_cont()), "UTF-8"));
				Cookie mbr_addr = new Cookie("mbr_addr", URLEncoder.encode(String.valueOf(loginUser.getMbr_addr()), "UTF-8"));
				Cookie mbr_web = new Cookie("mbr_web", URLEncoder.encode(String.valueOf(loginUser.getMbr_web()), "UTF-8"));
				Cookie mbr_twit = new Cookie("mbr_twit", URLEncoder.encode(String.valueOf(loginUser.getMbr_twit()), "UTF-8"));
				Cookie mbr_insta = new Cookie("mbr_insta", URLEncoder.encode(String.valueOf(loginUser.getMbr_insta()), "UTF-8"));
				Cookie mbr_face = new Cookie("mbr_face", URLEncoder.encode(String.valueOf(loginUser.getMbr_face()), "UTF-8"));
				Cookie mbr_brd = new Cookie("mbr_brd", URLEncoder.encode(String.valueOf(loginUser.getMbr_brd()), "UTF-8"));
				Cookie sys_reg_date = new Cookie("sys_reg_date", URLEncoder.encode(String.valueOf(loginUser.getSys_reg_date()), "UTF-8"));

				res.addCookie(mbr_no);
				res.addCookie(mbr_nm);
				res.addCookie(mbr_email);
				res.addCookie(mbr_pw);
				res.addCookie(mbr_phone);
				res.addCookie(mbr_cont);
				res.addCookie(mbr_addr);
				res.addCookie(mbr_web);
				res.addCookie(mbr_twit);
				res.addCookie(mbr_insta);
				res.addCookie(mbr_face);
				res.addCookie(mbr_brd);
				res.addCookie(sys_reg_date);
				return new RedirectView("http://localhost:3000/Main");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RedirectView("http://localhost:3000/Login");

	}

	@RequestMapping("/updateMbrInfo.staff")
	public void goLogin(HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res) throws Exception {
		// 1, 파라미터 받아오기
		String mbr_no = req.getParameter("mbr_no");
		String mbr_nm = req.getParameter("mbr_nm");
		String mbr_email = req.getParameter("mbr_email");
		String mbr_pw = req.getParameter("mbr_pw");
		String mbr_brd = req.getParameter("mbr_brd");
		String mbr_phone = req.getParameter("mbr_phone");
		String mbr_cont = req.getParameter("mbr_cont");
		String mbr_addr = req.getParameter("mbr_addr");
		String mbr_web = req.getParameter("mbr_web");
		String mbr_twit = req.getParameter("mbr_twit");
		String mbr_face = req.getParameter("mbr_face");
		String mbr_insta = req.getParameter("mbr_insta");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		UserVO uptUserVO = new UserVO();
		uptUserVO.setMbr_no(Integer.valueOf(mbr_no));
		uptUserVO.setMbr_nm(mbr_nm);
		uptUserVO.setMbr_email(mbr_email);
		uptUserVO.setMbr_pw(mbr_pw);
		uptUserVO.setMbr_brd(formatter.parse(mbr_brd));
		uptUserVO.setMbr_phone(mbr_phone);
		uptUserVO.setMbr_cont(mbr_cont);
		uptUserVO.setMbr_addr(mbr_addr);
		uptUserVO.setMbr_web(mbr_web);
		uptUserVO.setMbr_twit(mbr_twit);
		uptUserVO.setMbr_face(mbr_face);
		uptUserVO.setMbr_insta(mbr_insta);
		System.out.println(uptUserVO.toString());

		int check = userService.updateMbrInfo(uptUserVO);

		// 2. 서비스 전송

		// 3. 성공일 경우
//		goLogin(req, rttr, res);

	}

}
