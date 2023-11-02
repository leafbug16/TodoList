package com.leafbug.todolist.service;

import java.util.List;

import com.leafbug.todolist.model.SearchCondition;
import com.leafbug.todolist.model.User;

public interface UserService {

	//ȸ�� ����
	int addUser(User user) throws Exception;

	//�α��� id, pwd ����
	User findUser(String id) throws Exception;

	//user ����
	int removeUser(String id) throws Exception;

	List<User> getUserAll(SearchCondition sc) throws Exception;

	int getCntUserAll(SearchCondition sc) throws Exception;

}