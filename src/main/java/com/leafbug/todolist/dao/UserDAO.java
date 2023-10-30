package com.leafbug.todolist.dao;

import com.leafbug.todolist.model.User;

public interface UserDAO {

	//회원가입
	int insert(User user) throws Exception;

	//로그인 id, pwd 대조 체크
	User select(User user) throws Exception;
	
	//유저 삭제
	int delete(User user) throws Exception;

}