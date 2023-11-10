<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todolist</title>
</head>
<body>
	<h1>todo</h1>
	<h1>현재 로그인 : ${sessionId }</h1>
	<a href="<c:url value='/login/logout'/>">로그아웃</a><br><br>
	<a href="<c:url value='/board/listGuide'/>">가이드</a>
    <a href="<c:url value='/board/listNotice'/>"> 공지사항</a>
    <a href="<c:url value='/board/listFree'/>"> 자유게시판</a><br><br>
    <a href="<c:url value='/board/listMyLike'/>">마이페이지</a>
    <c:if test="${sessionId eq 'admin' }">
    	<a href="<c:url value='/board/listAll'/>"> 관리자 페이지</a><br><br>
    </c:if>
    <a href="<c:url value='/board/writeReport'/>">문의 / bug report</a>
    
    <!-- 목록 가져오기 테스트 -->
    <ul>
    	<c:forEach var="list" items="${lists }">
    		<li><a href="<c:url value='/todolist/read?lno=${list.lno }'/>">${list.title }</a></li>
    	</c:forEach>
    </ul>
    
     
</body>
</html>