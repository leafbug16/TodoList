package com.leafbug.todolist.model;

import java.util.Date;

public class Todo {
	private Integer tno;
	private Integer lno;
	private String todoType;
	private String content;
	private String complete;
	private String userId;
	private Date startDate;
	private Date endDate;
	
	public Todo() {
		super();
	}
	
	public Integer getTno() {
		return tno;
	}
	public void setTno(Integer tno) {
		this.tno = tno;
	}
	public Integer getLno() {
		return lno;
	}
	public void setLno(Integer lno) {
		this.lno = lno;
	}
	public String getTodoType() {
		return todoType;
	}
	public void setTodoType(String todoType) {
		this.todoType = todoType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Todo [tno=" + tno + ", lno=" + lno + ", todoType=" + todoType + ", content=" + content + ", complete="
				+ complete + ", userId=" + userId + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
