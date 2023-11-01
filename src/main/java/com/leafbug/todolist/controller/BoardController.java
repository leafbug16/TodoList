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

import com.leafbug.todolist.model.Board;
import com.leafbug.todolist.model.PageHandler;
import com.leafbug.todolist.model.SearchCondition;
import com.leafbug.todolist.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	//모든 게시글 조회
	@GetMapping("/listAll")
	public String listAll(SearchCondition sc, HttpServletRequest request, Model m) {	
		
		try {
			int totalCnt = boardService.getSearchResultCntAll(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<Board> list = boardService.getSearchResultPageAll(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			
			System.out.println("보드컨트롤러에서 리스트 확인 : " +list);
			
			Date now = new Date();
			m.addAttribute("now", now);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board";
	}
	
	@GetMapping("/listGuide")
	public String listGuide(SearchCondition sc, HttpServletRequest request, Model m) {	
		
		try {
			int totalCnt = boardService.getSearchResultCntGuide(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<Board> list = boardService.getSearchResultPageGuide(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			
			Date now = new Date();
			m.addAttribute("now", now);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "boardGuide";
	}
	
	@GetMapping("/listNotice")
	public String listNotice(SearchCondition sc, HttpServletRequest request, Model m) {	
		
		try {
			int totalCnt = boardService.getSearchResultCntNotice(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<Board> list = boardService.getSearchResultPageNotice(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			
			Date now = new Date();
			m.addAttribute("now", now);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "boardNotice";
	}
	
	@GetMapping("/listFree")
	public String list(SearchCondition sc, HttpServletRequest request, Model m) {	
		
		try {
			int totalCnt = boardService.getSearchResultCntFree(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<Board> list = boardService.getSearchResultPageFree(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			
			Date now = new Date();
			m.addAttribute("now", now);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "boardFree";
	}
	
	@GetMapping("/read")
	public String read(Integer bno, SearchCondition sc, String mode, HttpServletRequest request, Model m) {
		Board board;
		HttpSession session = request.getSession();
		String sessionId = session.getAttribute("id")+"";
		try {
			board = boardService.read(bno);
			m.addAttribute("board", board);
			m.addAttribute("sessionId", sessionId);
		} catch (Exception e) {
			System.out.println("board/read 오류");
			e.printStackTrace();
			if("guide".equals(mode)) {
				return "redirect:/board/listGuide"+sc.getQueryString();
			} else if("notice".equals(mode)) {
				return "redirect:/board/listNotice"+sc.getQueryString();
			} else {
				return "redirect:/board/listFree"+sc.getQueryString();		
			}
		}
		if("guide".equals(mode)) {
			return "viewGuide";
		} else if("notice".equals(mode)) {
			return "viewNotice";
		} else {
			return "viewFree";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}








































