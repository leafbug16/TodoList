package com.leafbug.todolist.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.leafbug.todolist.model.PageHandler2;
import com.leafbug.todolist.model.SearchCondition;
import com.leafbug.todolist.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	//��� �Խñ� ��ȸ
	@GetMapping("/listAll")
	public String listAll(SearchCondition sc, HttpServletRequest request, Model m) {	
		
		try {
			int totalCnt = boardService.getSearchResultCntAll(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<Board> list = boardService.getSearchResultPageAll(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);

			Date now = new Date();
			m.addAttribute("now", now);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminBoard";
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
	
	@GetMapping("/listReported")
	public String listReported(SearchCondition sc, HttpServletRequest request, Model m, HttpSession session) {	
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		
		try {
			int totalCnt = boardService.getSearchResultCntReport(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<Board> list = boardService.getSearchResultPageReport(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			
			Date now = new Date();
			m.addAttribute("now", now);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminReported";
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
			System.out.println("board/read ����");
			e.printStackTrace();
			
			//list"+mode�� ����ȭ �� ����
			if("guide".equals(mode)) {
				return "redirect:/board/listGuide"+sc.getQueryString();
			} else if("notice".equals(mode)) {
				return "redirect:/board/listNotice"+sc.getQueryString();
			} else if("free".equals(mode)) {
				return "redirect:/board/listFree"+sc.getQueryString();
			} else if("myLike".equals(mode)) {
				return "redirect:/board/listMyLike";
			} else if("myReport".equals(mode)) {
				return "redirect:/board/listMyReport";
			} else {
				return "redirect:/board/listFree"+sc.getQueryString();		
			}
		}
		if("guide".equals(mode)) {
			return "viewGuide";
		} else if("notice".equals(mode)) {
			return "viewNotice";
		} else if("myReport".equals(mode)) {
			return "viewReport";
		} else {
			return "viewFree";
		}
	}
	
	//�Խ��� �۾���� �̵�
	@GetMapping("/write")
	public String write(Model m, HttpSession session) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		return "write";
	}
	
	//bug report �۾���� �̵�
	@GetMapping("/writeReport")
	public String writeReport(Model m, HttpSession session) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		return "writeReport";
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
			} else if("report".equals(boardType)) {
				return "redirect:/board/listMyReport";
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
	
	//�� ����
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
	
	@RequestMapping("/remove")
	public String remove(Integer bno, SearchCondition sc, String mode, HttpSession session, RedirectAttributes redatt) {
		try {
			int rowCnt = boardService.remove(bno);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
			} else {
				throw new Exception("board remove error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		if("guide".equals(mode)) {
			return "redirect:/board/listGuide";
		} else if("notice".equals(mode)) {
			return "redirect:/board/listNotice";
		} else if("free".equals(mode)) {
			return "redirect:/board/listFree";
		} else if("adminBoard".equals(mode)) {
			return "redirect:/board/listAll";
		} else if("adminReported".equals(mode)) {
			return "redirect:/board/listReported";
		} else {
			return "redirect:/board/listFree";		
		}

	}
	
	@GetMapping("/listMyLike")
	public String listLike(Integer page, Integer pageSize, HttpSession session, Model m) {
		if(page==null) page = 1;
		if(pageSize==null) pageSize = 15;
		try {
			Map map = new HashMap();
			map.put("id", session.getAttribute("id")+"");
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			
			int totalCnt = boardService.getResultCntLike(map);
			PageHandler2 ph = new PageHandler2(session.getAttribute("id")+"", totalCnt, page, pageSize);
			m.addAttribute("ph", ph);			
			
			List<Board> list = boardService.getResultPageLike(map);
			m.addAttribute("list", list);
			m.addAttribute("sessionId", session.getAttribute("id")+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myLike";
	}
	
	@GetMapping("/listMyPost")
	public String listMyPost(Integer page, Integer pageSize, HttpSession session, Model m) {
		if(page==null) page = 1;
		if(pageSize==null) pageSize = 15;
		try {
			Map map = new HashMap();
			map.put("id", session.getAttribute("id")+"");
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			
			int totalCnt = boardService.getCntMyPost(map);
			PageHandler2 ph = new PageHandler2(session.getAttribute("id")+"", totalCnt, page, pageSize);
			m.addAttribute("ph", ph);			
			
			List<Board> list = boardService.getMyPost(map);
			m.addAttribute("list", list);
			//ȸ��Ż��� ���� ���̵�
			m.addAttribute("sessionId", session.getAttribute("id")+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myPost";
	}
	
	@GetMapping("/listMyReport")
	public String listMyReport(Integer page, Integer pageSize, HttpSession session, Model m) {
		if(page==null) page = 1;
		if(pageSize==null) pageSize = 15;
		try {
			Map map = new HashMap();
			map.put("id", session.getAttribute("id")+"");
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			
			int totalCnt = boardService.getCntMyReport(map);
			PageHandler2 ph = new PageHandler2(session.getAttribute("id")+"", totalCnt, page, pageSize);
			m.addAttribute("ph", ph);			
			
			List<Board> list = boardService.getMyReport(map);
			m.addAttribute("list", list);
			//ȸ��Ż��� ���� ���̵�
			m.addAttribute("sessionId", session.getAttribute("id")+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myReport";
	}
	
	@GetMapping("/listMyComment")
	public String listMyComment(Integer page, Integer pageSize, HttpSession session, Model m) {
		if(page==null) page = 1;
		if(pageSize==null) pageSize = 15;
		try {
			Map map = new HashMap();
			map.put("id", session.getAttribute("id")+"");
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			
			int totalCnt = boardService.getCntMyComment(map);
			PageHandler2 ph = new PageHandler2(session.getAttribute("id")+"", totalCnt, page, pageSize);
			m.addAttribute("ph", ph);			
			
			List<Board> list = boardService.getMyComment(map);
			m.addAttribute("list", list);
			//ȸ��Ż��� ���� ���̵�
			m.addAttribute("sessionId", session.getAttribute("id")+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myComment";
	}

	
}

















