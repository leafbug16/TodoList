package com.leafbug.todolist.service;

import java.util.List;
import java.util.Map;

import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;

public interface TodolistService {

	//유저 아이디에 맞는 할일목록 리스트 가져오기
	List<Todolist> getLists(Map map) throws Exception;

	//할일목록 하나 가져오기
	Todolist getlist(Integer lno) throws Exception;

	//할일목록 늘리기
	int write(Map map) throws Exception;

	//할일목록 제목 수정
	int modify(Map map) throws Exception;

	//할일목록 하나 삭제
	int remove(Map map) throws Exception;

	//할일목록 전체 삭제
	int removeAll(Map map) throws Exception;
	
	//목록 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//할일 전체 가져오기
	List<Todo> getTodoLists(Todo todo) throws Exception;
	
	//할일 추가하기
	int writeTodo(Todo todo) throws Exception;
	
	//할일 수정하기
	int modifyTodo(Todo todo) throws Exception;
	
	//할일 삭제하기
	int removeTodo(Todo todo) throws Exception;
	
	//할일 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//메모 가져오기
	Todolist getMemo(Map map) throws Exception;
	
	//메모 입력하기
	int modifyMemo(Todolist todolist) throws Exception;
	
}




























