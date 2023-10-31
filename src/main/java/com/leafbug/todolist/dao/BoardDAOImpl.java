package com.leafbug.todolist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafbug.todolist.model.Board;
import com.leafbug.todolist.model.SearchCondition;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	SqlSession session;
	String namespace = "boardMapper.";
	
	@Override
	public List<Board> searchSelectPageAll(SearchCondition sc) throws Exception {
		return session.selectList(namespace+"searchSelectPageAll", sc);
	}
	
	@Override
	public List<Board> searchSelectPageGuide(SearchCondition sc) throws Exception {
		return session.selectList(namespace+"searchSelectPageGuide", sc);
	}
	
	@Override
	public List<Board> searchSelectPageNotice(SearchCondition sc) throws Exception {
		return session.selectList(namespace+"searchSelectPageNotice", sc);
	}
	
	@Override
	public List<Board> searchSelectPageFree(SearchCondition sc) throws Exception {
		return session.selectList(namespace+"searchSelectPageFree", sc);
	}
	
	@Override
	public int searchResultCntAll(SearchCondition sc) throws Exception {
		return session.selectOne(namespace+"searchResultCntAll", sc);
	}
	
	@Override
	public int searchResultCntGuide(SearchCondition sc) throws Exception {
		return session.selectOne(namespace+"searchResultCntGuide", sc);
	}
	
	@Override
	public int searchResultCntNotice(SearchCondition sc) throws Exception {
		return session.selectOne(namespace+"searchResultCntNotice", sc);
	}
	
	@Override
	public int searchResultCntFree(SearchCondition sc) throws Exception {
		return session.selectOne(namespace+"searchResultCntFree", sc);
	}
	
	//게시글 상세보기
	@Override
	public Board select(Integer bno) throws Exception {
		return session.selectOne(namespace+"select", bno);
	}
	//조회수 증가
	@Override
	public int increaseViews(Integer bno) throws Exception {
		return session.update(namespace+"increaseViews", bno);
	}
	
	
}
