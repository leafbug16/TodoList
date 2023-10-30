package com.leafbug.todolist.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
	public User select(String id) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		return session.selectOne(namespace+"select", map);
	}
	
	//유저 삭제
	@Override
	public int delete(String id) throws Exception {
		Map map = new HashMap();
		map.put("id", id);
		return session.delete(namespace+"delete", map);
	}
}
