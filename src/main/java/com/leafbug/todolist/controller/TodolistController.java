package com.leafbug.todolist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;
import com.leafbug.todolist.service.TodolistService;

@Controller
@RequestMapping("/todolist")
public class TodolistController {
	@Autowired
	TodolistService ts;
	
	//메인화면 띄우기, 목록들 가져오기
	@GetMapping("/main")
	public String main(HttpSession session, Model m) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		String id = session.getAttribute("id")+"";
		try {
			Map map = new HashMap();
			map.put("id", id);
			List<Todolist> lists = ts.getLists(map);
			m.addAttribute("lists", lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "todolist";
	}
	
	//목록 하나 클릭(read)
	@GetMapping("/read")
	public String read(Integer lno, HttpSession session, Model m) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		String id = session.getAttribute("id")+"";
		Todolist tl;
		try {
			
			Map map = new HashMap();
			map.put("id", id);
			List<Todolist> lists = ts.getLists(map);
			m.addAttribute("lists", lists);
			
			tl = ts.getlist(lno);
			m.addAttribute("tl", tl);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/todolist/main";
		}
		return "todolistView";
	}
	
	//할일목록 추가 후 리스트 다시 가져오기
	@GetMapping("/write")
	public String write(HttpSession session, Model m) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		String id = session.getAttribute("id")+"";
		try {
			Map map = new HashMap();
			map.put("id", id);
			int res = ts.write(map);
			List<Todolist> lists = ts.getLists(map);
			m.addAttribute("lists", lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "todolist";
	}
	
	//할일목록 제목 수정
	@GetMapping("/modify")
	public String modify(Integer lno, String title, HttpSession session, Model m) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		String id = session.getAttribute("id")+"";
		try {
			Map map = new HashMap();
			map.put("id", id);
			map.put("title", title);
			map.put("lno", lno);
			int res = ts.modify(map);
			List<Todolist> lists = ts.getLists(map);
			m.addAttribute("lists", lists);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/todolist/main";
		}
		return "todolist";
	}
	
	//할일목록 하나 삭제
	@GetMapping("/remove")
	public String remove(Integer lno, HttpSession session, Model m) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		String id = session.getAttribute("id")+"";
		try {
			Map map = new HashMap();
			map.put("id", id);
			map.put("lno", lno);
			int res = ts.remove(map);
			List<Todolist> lists = ts.getLists(map);
			m.addAttribute("lists", lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "todolist";
	}
	
	//할일목록 전체 삭제
	@GetMapping("/removeAll")
	public String removeAll(HttpSession session, Model m) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		String id = session.getAttribute("id")+"";
		try {
			Map map = new HashMap();
			map.put("id", id);
			int res = ts.removeAll(map);
			List<Todolist> lists = ts.getLists(map);
			m.addAttribute("lists", lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "todolist";
	}
	
	//목록 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//특정 목록 할일 모두 조회
	@GetMapping("/todos")
	@ResponseBody
	public ResponseEntity<List<Todo>> todos(@RequestParam Integer lno) {
		List<Todo> todoLists = null;
		Todo todo = new Todo();
		todo.setLno(lno);
		try {
			todoLists = ts.getTodoLists(todo);
			return new ResponseEntity<List<Todo>>(todoLists, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Todo>>(todoLists, HttpStatus.BAD_REQUEST);
	}
	
	//할일 추가
	@PostMapping("/todos")
	@ResponseBody
	public ResponseEntity<String> writeTodos(@RequestBody Todo todo, HttpSession session) {
		todo.setUserId(session.getAttribute("id")+"");
		try {
			int res = ts.writeTodo(todo);
			if(res != 1) throw new Exception("Write Error");
			return new ResponseEntity<>("WRITE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("WRITE_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//할일 수정
	@PatchMapping("/todos")
	@ResponseBody
	public ResponseEntity<String> modifyTodos(@RequestParam Integer tno, @RequestParam String content, HttpSession session) {
		Todo todo = new Todo();
		todo.setTno(tno);
		todo.setContent(content);
		try {
			int res = ts.modifyTodo(todo);
			if(res != 1) throw new Exception("Modify Error");
			return new ResponseEntity<>("MODIFY_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("MODIFY_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//할일 삭제
	@DeleteMapping("/todos")
	@ResponseBody
	public ResponseEntity<String> removeTodos(@RequestParam Integer tno, HttpSession session) {
		Todo todo = new Todo();
		todo.setTno(tno);
		try {
			int res = ts.removeTodo(todo);
			if(res != 1) throw new Exception("Delete Error");
			return new ResponseEntity<>("DELETE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("DELETE_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
}































