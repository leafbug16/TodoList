package com.leafbug.todolist.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leafbug.todolist.model.User;
import com.leafbug.todolist.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping("/form")
	public String loginForm(HttpServletRequest request, Model m, HttpSession session) {
		if(session.getAttribute("id")!=null && !session.getAttribute("id").equals("")) {
			return "redirect:/todolist/main";
		}
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		return "index";
	}
	
	@PostMapping("/action")
	public String loginAction(String toURL, String id, String pwd, boolean rememberId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redatt) throws Exception {
		User user = userService.findUser(id);
		if (user == null || !user.getId().equals(id) || !user.getPwd().equals(pwd)) {
			redatt.addFlashAttribute("msg", "login-failed");
			return "redirect:/login/form";
		}
		
		if (rememberId) {
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
		session.setMaxInactiveInterval(60*60*24*365);
		return "redirect:/todolist/main";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login/form";
	}
}
