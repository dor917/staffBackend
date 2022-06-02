package com.staff.controller.PageController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.staff.model.UserVO;
import com.staff.service.UserService;
import com.staff.service.UserServiceImpl;

@RestController
@MapperScan(basePackages = "com.staff.service")
public class UserController {

	@Autowired(required=true)
	UserServiceImpl userService;

	@PostMapping("/gologin.staff")
	public RedirectView goLogin (UserVO userVO, HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res){
		try {
			HttpSession session = req.getSession();

			UserVO loginUser = userService.goLogin(userVO);
			
			if (loginUser == null) {
				rttr.addFlashAttribute("msg", false);
				
			} else {
				
				Cookie mbr_no = new Cookie("mbr_no", String.valueOf(loginUser.getMbr_no()));
				Cookie mbr_nm = new Cookie("mbr_nm",String.valueOf(loginUser.getMbr_nm()));
				Cookie mbr_email = new Cookie("mbr_email", String.valueOf(loginUser.getMbr_email()));
				Cookie mbr_pw = new Cookie("mbr_pw", String.valueOf(loginUser.getMbr_pw()));
				Cookie mbr_phone = new Cookie("mbr_phone", String.valueOf(loginUser.getMbr_phone()));
				Cookie mbr_cont = new Cookie("mbr_cont", String.valueOf(loginUser.getMbr_cont()));
				Cookie mbr_addr = new Cookie("mbr_addr", String.valueOf(loginUser.getMbr_addr()));
				Cookie mbr_web = new Cookie("mbr_web",  String.valueOf(loginUser.getMbr_web()));
				Cookie mbr_twit = new Cookie("mbr_twit",  String.valueOf(loginUser.getMbr_twit()));
				Cookie mbr_insta = new Cookie("mbr_insta", String.valueOf(loginUser.getMbr_insta()));
				Cookie mbr_face = new Cookie("mbr_face", String.valueOf(loginUser.getMbr_face()));
				Cookie mbr_brd = new Cookie("mbr_brd", String.valueOf(loginUser.getMbr_brd()));
				Cookie sys_reg_date = new Cookie("sys_reg_date", String.valueOf(loginUser.getSys_reg_date()));
				
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

//	@PostMapping("/gologin.staff")
//	public RedirectView Login(UserVO userVO, HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res){
////		try {
////			HttpSession session = req.getSession();
////
////			UserVO loginUser = userDAO.Login(userVO);
////			
////			if (loginUser == null) {
////				rttr.addFlashAttribute("msg", false);
////				
////			} else {
////				session.setAttribute("userNo", loginUser.getNo());
////				session.setAttribute("userId", loginUser.getId());
////				session.setAttribute("userPassword", loginUser.getPassword());
////				Cookie userNoCookie = new Cookie("USERNO", 
////						URLEncoder.encode(loginUser.getNo(),"utf-8" ));
////				Cookie userIdCookie = new Cookie("USERID", 
////						URLEncoder.encode(loginUser.getId(), "utf-8" ));
//////				Cookie userPwCookie = new Cookie("USER", 
//////						URLEncoder.encode("userNo:" + loginUser.getNo()+";"+"userId:" + loginUser.getId()+";"+"userPw:" + loginUser.getPassword()+";",
//////								"utf-8" ));
////				res.addCookie(userNoCookie);
////				res.addCookie(userIdCookie);
////				return new RedirectView("http://localhost:3000/Main");
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//		return new RedirectView("http://localhost:3000/Login");
//		
//
//	}

	@GetMapping("/hello")
	public String hello(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return (String) session.getAttribute("userId");
	}

}
