package com.leafbug.todolist.service;

import com.leafbug.todolist.model.User;

public interface UserService {

	//회원 가입
	int addUser(User user) throws Exception;

	//로그인 id, pwd 대조
	User findUser(String id) throws Exception;

	//user 삭제
	int removeUser(String id) throws Exception;

}