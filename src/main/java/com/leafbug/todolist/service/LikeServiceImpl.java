package com.leafbug.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbug.todolist.dao.BoardDAO;
import com.leafbug.todolist.dao.LikeDAO;
import com.leafbug.todolist.model.Like;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	LikeDAO likeDAO;
	
	//좋아요
	@Override
	public int addLike(Like like) throws Exception {
		boardDAO.updateLikeCnt(like.getBno(), 1);
		return likeDAO.insert(like);
	}
	
	//좋아요 해제
	@Override
	public int removeLike(Like like) throws Exception {
		boardDAO.updateLikeCnt(like.getBno(), -1);
		return likeDAO.delete(like);
	}
	
	//총 좋아요 개수 조회
	@Override
	public int getLikeCountAll(Integer bno) throws Exception {
		return likeDAO.countAll(bno);
	}
	
	//liker 판별용
	@Override
	public int getLikeCount(Integer bno, String liker) throws Exception {
		return likeDAO.count(bno, liker);
	}
	
	
}





























