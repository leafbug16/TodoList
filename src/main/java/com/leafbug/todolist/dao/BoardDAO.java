package com.leafbug.todolist.dao;

import java.util.List;

import com.leafbug.todolist.model.Board;
import com.leafbug.todolist.model.SearchCondition;

public interface BoardDAO {

	List<Board> searchSelectPageAll(SearchCondition sc) throws Exception;

	List<Board> searchSelectPageGuide(SearchCondition sc) throws Exception;

	List<Board> searchSelectPageNotice(SearchCondition sc) throws Exception;

	List<Board> searchSelectPageFree(SearchCondition sc) throws Exception;

	int searchResultCntAll(SearchCondition sc) throws Exception;

	int searchResultCntGuide(SearchCondition sc) throws Exception;

	int searchResultCntNotice(SearchCondition sc) throws Exception;

	int searchResultCntFree(SearchCondition sc) throws Exception;

	Board select(Integer bno) throws Exception;

	int increaseViews(Integer bno) throws Exception;

	int insert(Board board) throws Exception;

	int update(Board board) throws Exception;

	int delete(Integer bno) throws Exception;

	int updateCommentCnt(Integer bno, Integer comments);

	int updateLikeCnt(Integer bno, Integer likes);

}