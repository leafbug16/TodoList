package com.leafbug.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbug.todolist.dao.TodolistDAO;
import com.leafbug.todolist.model.Todolist;

@Service
public class TodolistServiceImpl {
	@Autowired
	TodolistDAO tDAO;
	
	//���� ���̵� �´� ���ϸ�� ����Ʈ ��������
	public List<Todolist> getList(Todolist todolist) throws Exception {
		return tDAO.selectAll(todolist);
	}
	
}






























