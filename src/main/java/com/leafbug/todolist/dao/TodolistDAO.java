package com.leafbug.todolist.dao;

import java.util.List;

import com.leafbug.todolist.model.Todolist;

public interface TodolistDAO {

	//유저 아이디에 맞는 할일목록 리스트 가져오기
	List<Todolist> selectAll(Todolist todolist) throws Exception;

	//할일목록 하나 가져오기
	Todolist select(Integer lno) throws Exception;

	//할일목록 늘리기
	int insert(Todolist todolist) throws Exception;

	//할일목록 제목 수정
	int update(Todolist todolist) throws Exception;

	//할일목록 하나 삭제
	int delete(Todolist todolist) throws Exception;

	//할일목록 전체 삭제
	int deleteAll(Todolist todolist) throws Exception;

}