package com.leafbug.todolist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;

@Repository
public class TodolistDAOImpl implements TodolistDAO {
	@Autowired
	SqlSession session;
	String namespace = "todolistMapper.";
	
	//유저 아이디에 맞는 할일목록 리스트 가져오기
	@Override
	public List<Todolist> selectAll(Map map) throws Exception {
		return session.selectList(namespace+"selectAll", map);
	}
	
	//할일목록 하나 가져오기
	@Override
	public Todolist select(Integer lno) throws Exception {
		return session.selectOne(namespace+"select", lno);
	}
	
	//할일목록 늘리기
	@Override
	public int insert(Map map) throws Exception {
		return session.insert(namespace+"insert", map);
	}
	
	//할일목록 제목 수정
	@Override
	public int update(Map map) throws Exception {
		return session.update(namespace+"update", map);
	}
	
	//할일목록 하나 삭제
	@Override
	public int delete(Map map) throws Exception {
		return session.delete(namespace+"delete", map);
	}
	
	//할일목록 전체 삭제
	@Override
	public int deleteAll(Map map) throws Exception {
		return session.delete(namespace+"deleteAll", map);
	}
	
	//목록 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//할일 모두 가져오기
	@Override
	public List<Todo> todoSelectAll(Todo todo) throws Exception {
		return session.selectList(namespace+"todoSelectAll", todo);
	}
	
	//할일 추가하기
	@Override
	public int todoInsert(Todo todo) throws Exception {
		return session.insert(namespace+"todoInsert", todo);
	}
	
	//할일 수정하기
	@Override
	public int todoUpdate(Todo todo) throws Exception {
		return session.update(namespace+"todoUpdate", todo);
	}
	
	//할일 삭제하기
	@Override
	public int todoDelete(Todo todo) throws Exception {
		return session.delete(namespace+"todoDelete", todo);
	}

}

































