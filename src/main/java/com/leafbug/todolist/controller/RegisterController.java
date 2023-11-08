package com.leafbug.todolist.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
			String msg = URLEncoder.encode("뭔가 잘못됐습니다", "UTF-8");
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
	
	@ResponseBody
	@GetMapping("/duplicateIdCheck")
	public ResponseEntity<Map<String, Object>> duplicateIdCheck(String id, HttpSession session) {
		System.out.println("컨트롤러로 오지도 못함?");
		Map<String, Object> map = new HashMap<>();
		int res = 0;
		try {
			User user = userService.findUser(id);
			if(user == null) {
				res = 0;
			} else {
				res = 1;
			}
			System.out.println(res);
			map.put("res", res);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
}














