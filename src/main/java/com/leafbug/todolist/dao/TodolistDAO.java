package com.leafbug.todolist.dao;

import java.util.List;

import com.leafbug.todolist.model.Todolist;

public interface TodolistDAO {

	//���� ���̵� �´� ���ϸ�� ����Ʈ ��������
	List<Todolist> selectAll(Todolist todolist) throws Exception;

	//���ϸ�� �ϳ� ��������
	Todolist select(Integer lno) throws Exception;

	//���ϸ�� �ø���
	int insert(Todolist todolist) throws Exception;

	//���ϸ�� ���� ����
	int update(Todolist todolist) throws Exception;

	//���ϸ�� �ϳ� ����
	int delete(Todolist todolist) throws Exception;

	//���ϸ�� ��ü ����
	int deleteAll(Todolist todolist) throws Exception;

}