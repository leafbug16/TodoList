package com.leafbug.todolist.service;

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
	public User findUser(User user) throws Exception {
		return userDAO.select(user);
	}
	
	//user 삭제
	@Override
	public int removeUser(User user) throws Exception {
		return userDAO.delete(user);
	}
	
}
