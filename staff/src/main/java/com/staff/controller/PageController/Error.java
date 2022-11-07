package com.staff.controller.PageController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class Error implements ErrorController {

	@CrossOrigin
	@RequestMapping("/error")
	public RedirectView ErrorPage(HttpServletRequest req) {

		return new RedirectView("http://localhost:3000/ErrorPage");
	}

}
