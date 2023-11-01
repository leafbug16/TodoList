package com.leafbug.todolist.service;

import com.leafbug.todolist.model.Like;

public interface LikeService {

	//좋아요
	int addLike(Like like) throws Exception;

	//좋아요 해제
	int removeLike(Like like) throws Exception;

	//총 좋아요 개수 조회
	int getLikeCountAll(Integer bno) throws Exception;

	//liker 판별용
	int getLikeCount(Integer bno, String liker) throws Exception;

}