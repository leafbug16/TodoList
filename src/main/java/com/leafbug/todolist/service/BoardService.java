package com.leafbug.todolist.service;

import java.util.List;

import com.leafbug.todolist.model.Board;
import com.leafbug.todolist.model.SearchCondition;

public interface BoardService {

	List<Board> getSearchResultPageAll(SearchCondition sc) throws Exception;

	List<Board> getSearchResultPageGuide(SearchCondition sc) throws Exception;

	List<Board> getSearchResultPageNotice(SearchCondition sc) throws Exception;

	List<Board> getSearchResultPageFree(SearchCondition sc) throws Exception;

	int getSearchResultCntAll(SearchCondition sc) throws Exception;

	int getSearchResultCntGuide(SearchCondition sc) throws Exception;

	int getSearchResultCntNotice(SearchCondition sc) throws Exception;

	int getSearchResultCntFree(SearchCondition sc) throws Exception;

	Board read(Integer bno) throws Exception;

	int write(Board board) throws Exception;

}