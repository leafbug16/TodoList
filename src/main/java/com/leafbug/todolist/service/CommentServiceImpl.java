package com.leafbug.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbug.todolist.dao.BoardDAO;
import com.leafbug.todolist.dao.CommentDAO;
import com.leafbug.todolist.model.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	private BoardDAO boardDAO;
	private CommentDAO commentDAO;
	public CommentServiceImpl() {}
	@Autowired
	public CommentServiceImpl(BoardDAO boardDAO, CommentDAO commentDAO) {
		this.boardDAO = boardDAO;
		this.commentDAO = commentDAO;
	}
	
	//Ư�� �Խñ� ��� �� ��ȸ
	@Override
	public int getCount(Integer bno) throws Exception {
		return commentDAO.count(bno);
	}
	
	//Ư�� �Խñ� ��� ��� ��ȸ
	@Override
	public List<Comment> getList(Integer bno) throws Exception {
		return commentDAO.selectAll(bno);
	}
	
	//Ư�� ��� �ϳ� ��ȸ
	@Override
	public Comment read(Integer cno) throws Exception {
		return commentDAO.select(cno);
	}
	
	//��� �ۼ�
	@Override
	public int write(Comment comment) throws Exception {
		boardDAO.updateCommentCnt(comment.getBno(), 1);
		return commentDAO.insert(comment);
	}
	
	//��� ����
	@Override
	public int modify(Comment comment) throws Exception {
		return commentDAO.update(comment);
	}
	
	//��� ����
	@Override
	public int remove(Integer cno, Integer bno, String commenter) throws Exception {
		int rowCnt = boardDAO.updateCommentCnt(bno, -1);
		rowCnt = commentDAO.delete(cno, commenter);
		return rowCnt;
	}
	
}
