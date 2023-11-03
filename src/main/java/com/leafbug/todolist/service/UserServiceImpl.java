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
	
	//ȸ������
	@Override
	public int addUser(User user) throws Exception {
		return userDAO.insert(user);
	}
	
	//�α��� id/pwd ����
	@Override
	public User findUser(String id) throws Exception {
		return userDAO.select(id);
	}
	
	//���� ����
	@Override
	public int removeUser(String id) throws Exception {
		return userDAO.delete(id);
	}
	
	//���� ��� ��ȸ
	@Override
	public List<User> getUserAll(SearchCondition sc) throws Exception {
		return userDAO.selectUserAll(sc);
	}
	
	//���� �� ��ȸ
	@Override
	public int getCntUserAll(SearchCondition sc) throws Exception {
		return userDAO.selectCntUserAll(sc);
	}
	
}
