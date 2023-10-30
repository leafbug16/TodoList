package com.leafbug.todolist.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbug.todolist.dao.UserDAO;
import com.leafbug.todolist.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	
	//회원 가입
	@Override
	public int addUser(User user) throws Exception {
		return userDAO.insert(user);
	}
	
	//로그인 id, pwd 대조
	@Override
	public User findUser(String id) throws Exception {
		return userDAO.select(id);
	}
	
	//user 삭제
	@Override
	public int removeUser(String id) throws Exception {
		return userDAO.delete(id);
	}
	
}
