package com.leafbug.todolist.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leafbug.todolist.model.Comment;

@Controller
public class LikeController {
	
	//좋아요 추가
	@PostMapping("/addlike")
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
	
}


























