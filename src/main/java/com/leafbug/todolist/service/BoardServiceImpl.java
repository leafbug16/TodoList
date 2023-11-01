package com.leafbug.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leafbug.todolist.dao.BoardDAO;
import com.leafbug.todolist.model.Board;
import com.leafbug.todolist.model.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	@Override
	public List<Board> getSearchResultPageAll(SearchCondition sc) throws Exception {
		return boardDAO.searchSelectPageAll(sc);
	}
	
	@Override
	public List<Board> getSearchResultPageGuide(SearchCondition sc) throws Exception {
		return boardDAO.searchSelectPageGuide(sc);
	}
	
	@Override
	public List<Board> getSearchResultPageNotice(SearchCondition sc) throws Exception {
		return boardDAO.searchSelectPageNotice(sc);
	}
	
	@Override
	public List<Board> getSearchResultPageFree(SearchCondition sc) throws Exception {
		return boardDAO.searchSelectPageFree(sc);
	}
	
	@Override
	public int getSearchResultCntAll(SearchCondition sc) throws Exception {
		return boardDAO.searchResultCntAll(sc);
	}
	
	@Override
	public int getSearchResultCntGuide(SearchCondition sc) throws Exception {
		return boardDAO.searchResultCntGuide(sc);
	}
	
	@Override
	public int getSearchResultCntNotice(SearchCondition sc) throws Exception {
		return boardDAO.searchResultCntNotice(sc);
	}
	
	@Override
	public int getSearchResultCntFree(SearchCondition sc) throws Exception {
		return boardDAO.searchResultCntFree(sc);
	}
	
	//게시판 읽기 = 게시판 조회 + 조회수 1증가
	@Override
	public Board read(Integer bno) throws Exception {
		Board board = boardDAO.select(bno); // 조회 후 읽었으므로 조회수 1 증가
		boardDAO.increaseViews(bno);
		return board;
	}
	
	//게시판 글 쓰기
	@Override
	public int write(Board board) throws Exception {
		return boardDAO.insert(board);
	}
	
	//수정
	@Override
	public int modify(Board board) throws Exception {
		return boardDAO.update(board);
	}
	
	//삭제
	@Override
	public int remove(Integer bno) throws Exception {
		return boardDAO.delete(bno);
	}
	
}
