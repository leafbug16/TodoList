package com.leafbug.todolist.service;

import java.util.List;

import com.leafbug.todolist.model.Comment;

public interface CommentService {

	//Ư�� �Խñ� ��� �� ��ȸ
	int getCount(Integer bno) throws Exception;

	//Ư�� �Խñ� ��� ��� ��ȸ
	List<Comment> getList(Integer bno) throws Exception;

	//Ư�� ��� �ϳ� ��ȸ
	Comment read(Integer cno) throws Exception;

	//��� �ۼ�
	int write(Comment comment) throws Exception;

	//��� ����
	int modify(Comment comment) throws Exception;

	//��� ����
	int remove(Integer cno, Integer bno, String commenter) throws Exception;

}