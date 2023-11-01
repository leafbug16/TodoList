package com.leafbug.todolist.dao;

import com.leafbug.todolist.model.Like;

public interface LikeDAO {

	int insert(Like like) throws Exception;

	int delete(Like like) throws Exception;

	int countAll(Integer bno) throws Exception;

	int count(Integer bno, String liker) throws Exception;

}