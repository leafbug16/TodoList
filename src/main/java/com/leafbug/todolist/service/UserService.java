package com.leafbug.todolist.service;

import com.leafbug.todolist.model.User;

public interface UserService {

	//ȸ�� ����
	int addUser(User user) throws Exception;

	//�α��� id, pwd ����
	User findUser(String id) throws Exception;

	//user ����
	int removeUser(String id) throws Exception;

}