package com.leafbug.todolist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbug.todolist.dao.TodolistDAO;
import com.leafbug.todolist.model.Todo;
import com.leafbug.todolist.model.Todolist;

@Service
public class TodolistServiceImpl implements TodolistService {
	@Autowired
	TodolistDAO tDAO;
	
	//유저 아이디에 맞는 할일목록 리스트 가져오기
	@Override
	public List<Todolist> getLists(Todolist todolist) throws Exception {
		return tDAO.selectAll(todolist);
	}
	
	//할일목록 하나 가져오기
	@Override
	public Todolist getlist(Integer lno) throws Exception {
		return tDAO.select(lno);
	}
	
	//할일목록 추가
	@Override
	public int write(Todolist todolist) throws Exception {
		return tDAO.insert(todolist);
	}
	
	//할일목록 제목 수정
	@Override
	public int modify(Todolist todolist) throws Exception {
		return tDAO.update(todolist);
	}
	
	//할일목록 하나 삭제
	@Override
	public int remove(Todolist todolist) throws Exception {
		return tDAO.delete(todolist);
	}
	
	//할일목록 전체 삭제
	@Override
	public int removeAll(Todolist todolist) throws Exception {
		return tDAO.deleteAll(todolist);
	}
	
	//목록 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//할일 모두 가져오기
	@Override
	public List<Todo> getTodoLists(Todo todo) throws Exception {
		return tDAO.todoSelectAll(todo);
	}
	
	//할일 추가하기
	@Override
	public int writeTodo(Todo todo) throws Exception {
		return tDAO.todoInsert(todo);
	}
	
	//할일 수정하기
	@Override
	public int modifyTodo(Todo todo) throws Exception {
		return tDAO.todoUpdate(todo);
	}
	
	//할일 삭제하기
	@Override
	public int removeTodo(Todo todo) throws Exception {
		return tDAO.todoDelete(todo);
	}
	
	//할일 끝 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	//메모 가져오기
	@Override
	public Todolist getMemo(Map map) throws Exception {
		return tDAO.memoSelect(map);
	}
	
	//메모 입력하기
	@Override
	public int modifyMemo(Todolist todolist) throws Exception {
		return tDAO.memoUpdate(todolist);
	}
	
	
}






























