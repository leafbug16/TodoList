package com.leafbug.todolist.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafbug.todolist.model.Like;

@Repository
public class LikeDAOImpl implements LikeDAO {
	@Autowired
	SqlSession session;
	
	String namespace = "likeMapper.";
	
	@Override
	public int insert(Like like) throws Exception {
		return session.insert(namespace+"insert", like);
	}
	
	@Override
	public int delete(Like like) throws Exception {
		return session.delete(namespace+"delete", like);
	}
	
	@Override
	public int countAll(Integer bno) throws Exception {
		return session.selectOne(namespace+"countAll", bno);
	}
	
	@Override
	public int count(Integer bno, String liker) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("liker", liker);
		return session.selectOne(namespace+"count", bno);
	}
	
}
