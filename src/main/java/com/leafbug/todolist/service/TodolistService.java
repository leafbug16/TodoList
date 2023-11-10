package com.leafbug.todolist.service;

import java.util.List;
import java.util.Map;

import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;

public interface TodolistService {

	//���� ���̵� �´� ���ϸ�� ����Ʈ ��������
	List<Todolist> getLists(Map map) throws Exception;

	//���ϸ�� �ϳ� ��������
	Todolist getlist(Integer lno) throws Exception;

	//���ϸ�� �ø���
	int write(Map map) throws Exception;

	//���ϸ�� ���� ����
	int modify(Map map) throws Exception;

	//���ϸ�� �ϳ� ����
	int remove(Map map) throws Exception;

	//���ϸ�� ��ü ����
	int removeAll(Map map) throws Exception;
	
	//��� �� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//���� ��ü ��������
	List<Todo> getTodoLists(Todo todo) throws Exception;
	
	//���� �߰��ϱ�
	int writeTodo(Todo todo) throws Exception;
	
	//���� �����ϱ�
	int modifyTodo(Todo todo) throws Exception;
	
	//���� �����ϱ�
	int removeTodo(Todo todo) throws Exception;
	
	//���� �� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//�޸� ��������
	Todolist getMemo(Map map) throws Exception;
	
	//�޸� �Է��ϱ�
	int modifyMemo(Todolist todolist) throws Exception;
	
}




























