package com.leafbug.todolist.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");
	
//		Object obj = context.getBean("dataSource");
		System.out.println(	context.containsBean("transactionManager"));
		
	}
}
