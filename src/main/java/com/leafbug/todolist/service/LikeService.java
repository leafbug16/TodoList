package com.leafbug.todolist.service;

import com.leafbug.todolist.model.Like;

public interface LikeService {

	//���ƿ�
	int addLike(Integer bno, String liker) throws Exception;

	//���ƿ� ����
	int removeLike(Integer bno, String liker) throws Exception;

	//�� ���ƿ� ���� ��ȸ
	int getLikeCountAll(Integer bno) throws Exception;

	//liker �Ǻ���
	int getLikeCount(Integer bno, String liker) throws Exception;



}