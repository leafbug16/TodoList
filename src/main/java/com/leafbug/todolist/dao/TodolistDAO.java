package com.leafbug.todolist.dao;

import java.util.List;
import java.util.Map;

import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;

public interface TodolistDAO {

	//유저 아이디에 맞는 할일목록 리스트 가져오기
	List<Todolist> selectAll(Map map) throws Exception;

	//할일목록 하나 가져오기
	Todolist select(Integer lno) throws Exception;

	//할일목록 늘리기
	int insert(Map map) throws Exception;

	//할일목록 제목 수정
	int update(Map map) throws Exception;

	//할일목록 하나 삭제
	int delete(Map map) throws Exception;

	//할일목록 전체 삭제
	int deleteAll(Map map) throws Exception;
	
	//목록 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//할일 모두 가져오기
	List<Todo> todoSelectAll(Todo todo) throws Exception;
	
	//할일 추가하기
	int todoInsert(Todo todo) throws Exception;
	
	//할일 수정하기
	int todoUpdate(Todo todo) throws Exception;
	
	//할일 삭제하기
	int todoDelete(Todo todo) throws Exception;
	
	//할일 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//메모 가져오기
	Todolist memoSelect(Map map) throws Exception;
	
	//메모 입력하기
	int memoUpdate(Todolist todolist) throws Exception;

}




















