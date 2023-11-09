package com.leafbug.todolist.model;

public class Todolist {
	private Integer lno;
	private String title;
	private String userId;
	
	public Todolist() {
		super();
	}
	public Todolist(String title, String userId) {
		super();
		this.title = title;
		this.userId = userId;
	}
	
	public Integer getLno() {
		return lno;
	}
	public void setLno(Integer lno) {
		this.lno = lno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Todolist [lno=" + lno + ", title=" + title + ", userId=" + userId + "]";
	}
	
}
