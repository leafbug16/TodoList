package com.leafbug.todolist.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leafbug.todolist.model.User;
import com.leafbug.todolist.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping("/form")
	public String loginForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("id")!=null && !session.getAttribute("id").equals("")) {
			return "home";
		}
		return "login";
	}
	
	@PostMapping("/action")
	public String loginAction(String toURL, String id, String pwd, boolean keepLogin, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = userService.findUser(id);
		if (!user.getId().equals(id) && !user.getPwd().equals(pwd)) {
			return "redirect:/login/form";
		}
		
		if (keepLogin) {
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60*60*24*365);
			/* cookie.setPath("/firstSpring"); */
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("id", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
}
