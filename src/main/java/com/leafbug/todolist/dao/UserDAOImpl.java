package com.leafbug.todolist.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.leafbug.todolist.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	SqlSession session;
	String namespace = "userMapper.";
	
	//회원가입
	@Override
	public int insert(User user) throws Exception {
		return session.insert(namespace+"insert", user);
	}
	
	//로그인 id, pwd 대조 체크
	@Override
	public User select(User user) throws Exception {
		return session.selectOne(namespace+"select", user);
	}
	
	//유저 삭제
	@Override
	public int delete(User user) throws Exception {
		return session.delete(namespace+"delete", user);
	}
}
