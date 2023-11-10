package com.leafbug.todolist.dao;

import java.util.List;
import java.util.Map;

import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;

public interface TodolistDAO {

	//���� ���̵� �´� ���ϸ�� ����Ʈ ��������
	List<Todolist> selectAll(Map map) throws Exception;

	//���ϸ�� �ϳ� ��������
	Todolist select(Integer lno) throws Exception;

	//���ϸ�� �ø���
	int insert(Map map) throws Exception;

	//���ϸ�� ���� ����
	int update(Map map) throws Exception;

	//���ϸ�� �ϳ� ����
	int delete(Map map) throws Exception;

	//���ϸ�� ��ü ����
	int deleteAll(Map map) throws Exception;
	
	//��� �� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//���� ��� ��������
	List<Todo> todoSelectAll(Todo todo) throws Exception;
	
	//���� �߰��ϱ�
	int todoInsert(Todo todo) throws Exception;
	
	//���� �����ϱ�
	int todoUpdate(Todo todo) throws Exception;
	
	//���� �����ϱ�
	int todoDelete(Todo todo) throws Exception;
	
	//���� �� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//�޸� ��������
	Todolist memoSelect(Map map) throws Exception;
	
	//�޸� �Է��ϱ�
	int memoUpdate(Todolist todolist) throws Exception;

}




















