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
	
	//todolist.jsp ���� �뵵
	@GetMapping("/main")
	public String main(HttpSession session, Model m) {
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		return "todolist";
	}
	
	//��� ��������(ajax)
	@GetMapping("/lists")
	@ResponseBody
	public ResponseEntity<List<Todolist>> lists(HttpSession session, Model m) {
		List<Todolist> lists = null;
		Todolist todolist = new Todolist();
		todolist.setUserId(session.getAttribute("id")+"");
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		try {
			lists = ts.getLists(todolist);	
			return new ResponseEntity<List<Todolist>>(lists, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Todolist>>(lists, HttpStatus.BAD_REQUEST);
		}
	}
	
	//��� �ϳ� Ŭ��(read)
	@GetMapping("/read")
	public String read(Integer lno, HttpSession session, Model m) {	
		List<Todolist> lists = null;
		Todolist todolist = new Todolist();
		todolist.setUserId(session.getAttribute("id")+"");
		m.addAttribute("sessionId", session.getAttribute("id")+"");
		try {
			lists = ts.getLists(todolist);
			m.addAttribute("lists", lists);
			
			todolist = ts.getlist(lno);
			m.addAttribute("tl", todolist);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/todolist/main";
		}
		return "todolistView";
	}
	
	//���ϸ�� �߰� ajax
	@PostMapping("/lists")
	@ResponseBody
	public ResponseEntity<String> write(HttpSession session) {
		Todolist todolist = new Todolist();
		todolist.setUserId(session.getAttribute("id")+"");
		try {
			int res = ts.write(todolist);
			if(res != 1) throw new Exception("Write List Error");
			return new ResponseEntity<>("LIST_WRITE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("LIST_WRITE_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//���ϸ�� ���� ����
//	@GetMapping("/modify")
//	public String modify(Integer lno, String title, HttpSession session, Model m) {
//		List<Todolist> lists = null;
//		Todolist todolist = new Todolist();
//		todolist.setUserId(session.getAttribute("id")+"");
//		m.addAttribute("sessionId", session.getAttribute("id")+"");
//		try {
//			Map map = new HashMap();
//			map.put("id", session.getAttribute("id")+"");
//			map.put("title", title);
//			map.put("lno", lno);
//			int res = ts.modify(map);
//			lists = ts.getLists(todolist);
//			m.addAttribute("lists", lists);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "redirect:/todolist/main";
//		}
//		return "todolist";
//	}
	
	//���ϸ�� ���� ���� ajax
	@PatchMapping("/lists")
	@ResponseBody
	public ResponseEntity<String> modifyLists(@RequestBody Todolist todolist, HttpSession session) {
		try {
			int res = ts.modify(todolist);
			if(res != 1) throw new Exception("List Modify Error");
			return new ResponseEntity<>("LIST_MODIFY_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("LIST_MODIFY_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//���� ��� ����
	@DeleteMapping("/lists")
	@ResponseBody
	public ResponseEntity<String> removeList(@RequestParam Integer lno, HttpSession session) {
		Todolist todolist = new Todolist();
		todolist.setLno(lno);
		try {
			int res = ts.remove(todolist);
			if(res != 1) throw new Exception("List Delete Error");
			return new ResponseEntity<>("LIST_DELETE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("LIST_DELETE_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//���� ��� ��ü ����
	@DeleteMapping("/listsAll")
	@ResponseBody
	public ResponseEntity<String> removeListAll(HttpSession session) {
		Todolist todolist = new Todolist();
		todolist.setUserId(session.getAttribute("id")+"");
		try {
			int res = ts.removeAll(todolist);
			if(res < 1) throw new Exception("List Delete Error");
			return new ResponseEntity<>("LIST_DELETE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("LIST_DELETE_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//��� �� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//Ư�� ��� ���� ��� ��ȸ
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
	
	//���� �߰�
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
	
	//���� ����
	@PatchMapping("/todos")
	@ResponseBody
	public ResponseEntity<String> modifyTodos(@RequestBody Todo todo, HttpSession session) {
		try {
			int res = ts.modifyTodo(todo);
			if(res != 1) throw new Exception("Modify Error");
			return new ResponseEntity<>("MODIFY_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("MODIFY_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//���� ����
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
	
	//���� �� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//�޸� ��������
	@GetMapping("/todosMemo")
	@ResponseBody
	public ResponseEntity<Todolist> todosMemo(@RequestParam Integer lno) {
		Todolist todolist = new Todolist();
		Map map = new HashMap();
		map.put("lno", lno);
		try {
			todolist = ts.getMemo(map);
			return new ResponseEntity<Todolist>(todolist, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Todolist>(todolist, HttpStatus.BAD_REQUEST);
		}
	}
	
	//�޸� �Է��ϱ�
	@PostMapping("/todosMemo")
	@ResponseBody
	public ResponseEntity<String> writeTodosMemo(@RequestBody Todolist todolist, HttpSession session) {
		try {
			int res = ts.modifyMemo(todolist);
			if(res != 1) throw new Exception("Memo Write Error");
			return new ResponseEntity<>("MEMO_WRITE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("MEMO_WRITE_ERR", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}































