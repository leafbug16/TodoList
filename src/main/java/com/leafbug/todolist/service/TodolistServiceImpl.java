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
	public List<Todolist> getLists(Todolist todolist) throws Exception {
		return tDAO.selectAll(todolist);
	}
	
	//���ϸ�� �ϳ� ��������
	@Override
	public Todolist getlist(Integer lno) throws Exception {
		return tDAO.select(lno);
	}
	
	//���ϸ�� �߰�
	@Override
	public int write(Todolist todolist) throws Exception {
		return tDAO.insert(todolist);
	}
	
	//���ϸ�� ���� ����
	@Override
	public int modify(Todolist todolist) throws Exception {
		return tDAO.update(todolist);
	}
	
	//���ϸ�� �ϳ� ����
	@Override
	public int remove(Todolist todolist) throws Exception {
		return tDAO.delete(todolist);
	}
	
	//���ϸ�� ��ü ����
	@Override
	public int removeAll(Todolist todolist) throws Exception {
		return tDAO.deleteAll(todolist);
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
	
	//���� �� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//�޸� ��������
	@Override
	public Todolist getMemo(Map map) throws Exception {
		return tDAO.memoSelect(map);
	}
	
	//�޸� �Է��ϱ�
	@Override
	public int modifyMemo(Todolist todolist) throws Exception {
		return tDAO.memoUpdate(todolist);
	}
	
	
}






























