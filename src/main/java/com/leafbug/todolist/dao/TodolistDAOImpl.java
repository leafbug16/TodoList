package com.leafbug.todolist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafbug.todolist.model.Todolist;

@Repository
public class TodolistDAOImpl implements TodolistDAO {
	@Autowired
	SqlSession session;
	String namespace = "todolistMapper.";
	
	//���� ���̵� �´� ���ϸ�� ����Ʈ ��������
	@Override
	public List<Todolist> selectAll(Todolist todolist) throws Exception {
		return session.selectList(namespace+"selectAll", todolist);
	}
	
	//���ϸ�� �ϳ� ��������
	@Override
	public Todolist select(Integer lno) throws Exception {
		return session.selectOne(namespace+"select", lno);
	}
	
	//���ϸ�� �ø���
	@Override
	public int insert(Todolist todolist) throws Exception {
		return session.insert(namespace+"insert", todolist);
	}
	
	//���ϸ�� ���� ����
	@Override
	public int update(Todolist todolist) throws Exception {
		return session.update(namespace+"update", todolist);
	}
	
	//���ϸ�� �ϳ� ����
	@Override
	public int delete(Todolist todolist) throws Exception {
		return session.delete(namespace+"delete", todolist);
	}
	
	//���ϸ�� ��ü ����
	@Override
	public int deleteAll(Todolist todolist) throws Exception {
		return session.delete(namespace+"deleteAll", todolist);
	}
	
}

































