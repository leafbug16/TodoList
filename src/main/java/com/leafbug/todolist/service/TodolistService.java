package com.leafbug.todolist.service;

import java.util.List;
import java.util.Map;

import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;

public interface TodolistService {

	//���� ���̵� �´� ���ϸ�� ����Ʈ ��������
	List<Todolist> getLists(Todolist todolist) throws Exception;

	//���ϸ�� �ϳ� ��������
	Todolist getlist(Integer lno) throws Exception;

	//���ϸ�� �ø���
	int write(Todolist todolist) throws Exception;

	//���ϸ�� ���� ����
	int modify(Todolist todolist) throws Exception;

	//���ϸ�� �ϳ� ����
	int remove(Todolist todolist) throws Exception;

	//���ϸ�� ��ü ����
	int removeAll(Todolist todolist) throws Exception;
	
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




























