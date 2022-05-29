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

import com.staff.dao.UserDAO;
import com.staff.model.UserVO;

@RestController
@MapperScan(basePackages = "com.staff.dao")
public class UserController {

	@Autowired
	UserDAO userDAO;

	@PostMapping("/login.do")
	public RedirectView Login(UserVO userVO, HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res){
//		try {
//			HttpSession session = req.getSession();
//
//			UserVO loginUser = userDAO.Login(userVO);
//			
//			if (loginUser == null) {
//				rttr.addFlashAttribute("msg", false);
//				
//			} else {
//				session.setAttribute("userNo", loginUser.getNo());
//				session.setAttribute("userId", loginUser.getId());
//				session.setAttribute("userPassword", loginUser.getPassword());
//				Cookie userNoCookie = new Cookie("USERNO", 
//						URLEncoder.encode(loginUser.getNo(),"utf-8" ));
//				Cookie userIdCookie = new Cookie("USERID", 
//						URLEncoder.encode(loginUser.getId(), "utf-8" ));
////				Cookie userPwCookie = new Cookie("USER", 
////						URLEncoder.encode("userNo:" + loginUser.getNo()+";"+"userId:" + loginUser.getId()+";"+"userPw:" + loginUser.getPassword()+";",
////								"utf-8" ));
//				res.addCookie(userNoCookie);
//				res.addCookie(userIdCookie);
//				return new RedirectView("http://localhost:3000/Main");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return new RedirectView("http://localhost:3000/Login");
		

	}

	@GetMapping("/hello")
	public String hello(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return (String) session.getAttribute("userId");
	}

}
