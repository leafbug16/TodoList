package com.leafbug.todolist.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//글 작성
	@Override
	public int insert(Board board) throws Exception {
		return session.insert(namespace+"insert", board);
	}
	
	//글 수정
	@Override
	public int update(Board board) throws Exception {
		return session.update(namespace+"update", board);
	}
	
	//글 삭제
	@Override
	public int delete(Integer bno) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		return session.delete(namespace+"delete", map);
	}
	
	//댓글 수 증감
	@Override
	public int updateCommentCnt(Integer bno, Integer comments) {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("comments", comments);
		return session.update(namespace+"updateCommentCnt", map);
	}
	
	
}
