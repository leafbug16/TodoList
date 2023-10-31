package com.leafbug.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todoList")
public class TodoListController {
	@GetMapping("/main")
	public String main() {
		return "todoList";
	}
}
