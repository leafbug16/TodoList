package com.leafbug.todolist.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leafbug.todolist.model.PageHandler;
import com.leafbug.todolist.model.SearchCondition;
import com.leafbug.todolist.model.User;
import com.leafbug.todolist.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userSerivce;
	
	//���� ��� ��ȸ
	@GetMapping("/listAll")
	public String listAll(SearchCondition sc, HttpServletRequest request, Model m, HttpSession session) {	
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		
		try {
			int totalCnt = userSerivce.getCntUserAll(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<User> list = userSerivce.getUserAll(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);

			Date now = new Date();
			m.addAttribute("now", now);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminUser";
	}
	
	//���� ����
	@RequestMapping("/remove")
	public String remove(String id, String mode, RedirectAttributes redatt) {
		try {
			int rowCnt = userSerivce.removeUser(id);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
			} else {
				throw new Exception("user remove error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		System.out.println(mode);
		if("selfRemove".equals(mode)) {
			System.out.println("�������� userController");
			return "redirect:/login/logout";
		} else {
			return "redirect:/user/listAll";
		}
	}
	
	
	
}




























