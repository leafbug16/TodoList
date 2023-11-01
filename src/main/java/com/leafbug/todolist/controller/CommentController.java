package com.leafbug.todolist.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leafbug.todolist.model.Comment;
import com.leafbug.todolist.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	CommentService cs;

	//Ư�� �Խñ� ��� ��� ��ȸ
	@GetMapping("/comments")
	@ResponseBody
	public ResponseEntity<List<Comment>> list2(Integer bno) {
		List<Comment> list = null;
		try {
			list = cs.getList(bno);
			return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Comment>>(list, HttpStatus.BAD_REQUEST);
	}
	
	//��� ����
	@DeleteMapping("/comments/{cno}")
	@ResponseBody
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
		String commenter = session.getAttribute("id")+"";
		try {
			int rowCnt = cs.remove(cno, bno, commenter);
			if(rowCnt!=1) throw new Exception("delete failed");
			return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//��� �ۼ�
	@PostMapping("/comments")
	@ResponseBody
	public ResponseEntity<String> write(@RequestBody Comment comment, Integer bno, HttpSession session) {
		String commenter = session.getAttribute("id")+"";
		comment.setCommenter(commenter);
		comment.setBno(bno);
		try {
			int cnt = cs.write(comment);
			if(cnt!=1) throw new Exception("Write Error");
			return new ResponseEntity<>("WRITE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("WRITE_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	//��� ����
	@PatchMapping("/comments/{cno}")
	@ResponseBody
	public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody Comment comment, HttpSession session) {
		String commenter = session.getAttribute("id")+"";
		comment.setCommenter(commenter);
		comment.setCno(cno);
		try {
			int cnt = cs.modify(comment);
			if(cnt!=1) throw new Exception("Modify Error");
			return new ResponseEntity<>("MODIFY_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("MODIFY_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
}










