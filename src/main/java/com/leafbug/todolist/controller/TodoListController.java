package com.leafbug.todolist.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todoList")
public class TodoListController {
	@GetMapping("/main")
	public String main(HttpSession session, Model m) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		return "todoList";
	}
}
