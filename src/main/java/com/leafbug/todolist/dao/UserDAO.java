package com.leafbug.todolist.dao;

import com.leafbug.todolist.model.User;

public interface UserDAO {

	//ȸ������
	int insert(User user) throws Exception;

	//�α��� id, pwd ���� üũ
	User select(User user) throws Exception;
	
	//���� ����
	int delete(User user) throws Exception;

}