package com.leafbug.todolist.controller;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leafbug.todolist.model.User;
import com.leafbug.todolist.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	UserService userService;
	
	@GetMapping("/form")
	public String form() {
		return "register"; 
	}
	
	@PostMapping("/action")
	public String action(User user, Model m) throws Exception {
		if(!isValid(user)) {
			String msg = URLEncoder.encode("¹º°¡ Àß¸øµÆ½À´Ï´Ù", "UTF-8");
			m.addAttribute("msg", msg);
			return "redirect:/register/form";
		}
		return "redirect:/login/form";
	}
	
	private boolean isValid(User user) throws Exception {
		int res = userService.addUser(user);
		if(res != 1) {
			return false;
		}
		return true;
	}
}
