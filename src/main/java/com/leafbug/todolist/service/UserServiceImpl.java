package com.leafbug.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbug.todolist.dao.UserDAO;
import com.leafbug.todolist.model.SearchCondition;
import com.leafbug.todolist.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	
	//회원가입
	@Override
	public int addUser(User user) throws Exception {
		return userDAO.insert(user);
	}
	
	//로그인 id/pwd 대조
	@Override
	public User findUser(String id) throws Exception {
		return userDAO.select(id);
	}
	
	//유저 삭제
	@Override
	public int removeUser(String id) throws Exception {
		return userDAO.delete(id);
	}
	
	//유저 목록 조회
	@Override
	public List<User> getUserAll(SearchCondition sc) throws Exception {
		return userDAO.selectUserAll(sc);
	}
	
	//유저 수 조회
	@Override
	public int getCntUserAll(SearchCondition sc) throws Exception {
		return userDAO.selectCntUserAll(sc);
	}
	
}
