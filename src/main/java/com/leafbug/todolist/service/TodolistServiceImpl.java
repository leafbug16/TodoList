package com.leafbug.todolist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbug.todolist.dao.TodolistDAO;
import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;

@Service
public class TodolistServiceImpl implements TodolistService {
	@Autowired
	TodolistDAO tDAO;
	
	//���� ���̵� �´� ���ϸ�� ����Ʈ ��������
	@Override
	public List<Todolist> getLists(Map map) throws Exception {
		return tDAO.selectAll(map);
	}
	
	//���ϸ�� �ϳ� ��������
	@Override
	public Todolist getlist(Integer lno) throws Exception {
		return tDAO.select(lno);
	}
	
	//���ϸ�� �߰�
	@Override
	public int write(Map map) throws Exception {
		return tDAO.insert(map);
	}
	
	//���ϸ�� ���� ����
	@Override
	public int modify(Map map) throws Exception {
		return tDAO.update(map);
	}
	
	//���ϸ�� �ϳ� ����
	@Override
	public int remove(Map map) throws Exception {
		return tDAO.delete(map);
	}
	
	//���ϸ�� ��ü ����
	@Override
	public int removeAll(Map map) throws Exception {
		return tDAO.deleteAll(map);
	}
	
	//��� �� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//���� ��� ��������
	@Override
	public List<Todo> getTodoLists(Todo todo) throws Exception {
		return tDAO.todoSelectAll(todo);
	}
	
	//���� �߰��ϱ�
	@Override
	public int writeTodo(Todo todo) throws Exception {
		return tDAO.todoInsert(todo);
	}
	
	//���� �����ϱ�
	@Override
	public int modifyTodo(Todo todo) throws Exception {
		return tDAO.todoUpdate(todo);
	}
	
	//���� �����ϱ�
	@Override
	public int removeTodo(Todo todo) throws Exception {
		return tDAO.todoDelete(todo);
	}
	
}






























