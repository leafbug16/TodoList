package com.leafbug.todolist.dao;

import java.util.List;

import com.leafbug.todolist.model.SearchCondition;
import com.leafbug.todolist.model.User;

public interface UserDAO {

	//회원가입
	int insert(User user) throws Exception;

	//로그인 id, pwd 대조 체크
	User select(String id) throws Exception;
	
	//유저 삭제
	int delete(String id) throws Exception;

	List<User> selectUserAll(SearchCondition sc) throws Exception;

	int selectCntUserAll(SearchCondition sc) throws Exception;

}