package com.leafbug.todolist.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String listGuide(SearchCondition sc, HttpServletRequest request, Model m, HttpSession session) {	
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		
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
	public String listNotice(SearchCondition sc, HttpServletRequest request, Model m, HttpSession session) {	
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		
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
	public String list(SearchCondition sc, HttpServletRequest request, Model m, HttpSession session) {	
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		
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
	
	@GetMapping("/write")
	public String write(Model m, HttpSession session) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		return "write";
	}
	
	@PostMapping("/write")
	public String save(Board board, Model m, HttpSession session, RedirectAttributes redatt) {
		String writer = session.getAttribute("id")+"";
		board.setWriter(writer);
		String boardType = board.getBoardType();
		try {
			int rowCnt = boardService.write(board);
			if(rowCnt!=1) {
				throw new Exception("Write Error");
			}
			redatt.addFlashAttribute("msg", "write_ok");
			if("guide".equals(boardType)) {
				return "redirect:/board/listGuide";
			} else if("notice".equals(boardType)) {
				return "redirect:/board/listNotice";
			} else {
				return "redirect:/board/listFree";		
			}
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("board", board);
			m.addAttribute("msg", "write_error");
			return "write";
		}
	}
	
	//글 수정
	@GetMapping("/modify")
	public String modify(Integer bno, Model m, HttpSession session, RedirectAttributes redatt) {
		try {
			Board board = boardService.read(bno);
			if (session.getAttribute("id") == null || !board.getWriter().equals(session.getAttribute("id"))) {
				redatt.addFlashAttribute("msg", "modify_error");
				return "redirect:/board/listFree";
			}
			m.addAttribute("board", board);
			m.addAttribute("sessionId", session.getAttribute("id")+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	
	@PostMapping("/modify")
	public String modifyy(Board board, Model m, HttpSession session, RedirectAttributes redatt) {
		String writer = session.getAttribute("id")+"";
		board.setWriter(writer);
		try {
			int rowCnt = boardService.modify(board);
			if(rowCnt!=1) throw new Exception("modify_Error");
			redatt.addFlashAttribute("msg", "modify_ok");
			return "redirect:/board/listFree";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("board", board);
			m.addAttribute("msg", "modify_error");
			return "edit";
		}
	}
	
	@PostMapping("/remove")
	public String remove(Integer bno, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		try {
			String writer = (String)session.getAttribute("id");
			int rowCnt = boardService.remove(bno);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/board/listFree";
			} else {
				throw new Exception("board remove error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/listFree"+sc.getQueryString();
	}

	
}








































