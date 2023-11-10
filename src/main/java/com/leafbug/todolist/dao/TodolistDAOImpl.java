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
	public List<Todolist> selectAll(Todolist todolist) throws Exception {
		return session.selectList(namespace+"selectAll", todolist);
	}
	
	//할일목록 하나 가져오기
	@Override
	public Todolist select(Integer lno) throws Exception {
		return session.selectOne(namespace+"select", lno);
	}
	
	//할일목록 추가
	@Override
	public int insert(Todolist todolist) throws Exception {
		return session.insert(namespace+"insert", todolist);
	}
	
	//할일목록 제목 수정
	@Override
	public int update(Todolist todolist) throws Exception {
		return session.update(namespace+"update", todolist);
	}
	
	//할일목록 하나 삭제
	@Override
	public int delete(Todolist todolist) throws Exception {
		return session.delete(namespace+"delete", todolist);
	}
	
	//할일목록 전체 삭제
	@Override
	public int deleteAll(Todolist todolist) throws Exception {
		return session.delete(namespace+"deleteAll", todolist);
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
	
	//할일 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//메모 가져오기
	@Override
	public Todolist memoSelect(Map map) throws Exception {
		return session.selectOne(namespace+"memoSelect", map);
	}
	
	//메모 입력하기
	@Override
	public int memoUpdate(Todolist todolist) throws Exception {
		return session.update(namespace+"memoInsert", todolist);
	}

}

































