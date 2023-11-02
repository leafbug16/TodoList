<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    <style>
    	#title {
    		display: inline-block;
    	}
    </style>
</head>

<body>
    <h2 id="title">관리자 페이지</h2>    
    <a href="<c:url value='/'/>">할일로 돌아가기</a><br>
    <h2>게시판 글 관리</h2>
    <a href="<c:url value='/board/listAll'/>">게시판 글 관리</a>
    <a href="<c:url value='/user/listAll'/>">회원 관리</a>
    <a href="<c:url value='/board/listReported'/>">문의/bug report 관리</a>
    <table>
        <thead>
            <tr>
                <th>카테고리</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>등록일</th>
                <th>조회</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="board" items="${list }">
            <tr>
                <td>${board.boardType }</td>
                <td><a href="<c:url value='/board/read?bno=${board.bno }&mode=myLike'/>">${board.title }</a></td>
                <td>${board.writer }</td>
                <fmt:formatDate value="${board.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="reg_date" />
                <td>${reg_date }</td>
                <td>${board.views }</td>
                <td><a href="<c:url value='/board/remove?bno=${board.bno }&page=${page }&pageSize=${pageSize }&mode=adminBoard'/>" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- 페이지네이션 시작 --> 
	<nav>
	  <ul>
	  	<c:if test="${ph.showPrev }">
		    <li>
		      <a href="<c:url value='/board/listMyLike?page=${ph.beginPage-1 }&pageSize=${ph.pageSize }'/>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
	    </c:if>
	    <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
	    	<li ${ph.page==i? 'active':'' }"><a href="<c:url value='/board/listMyLike?page=${i }&pageSize=${ph.pageSize }' />">${i }</a></li>
	    </c:forEach>
	    <c:if test="${ph.showNext }">
		    <li>
		      <a href="<c:url value='/board/listMyLike?page=${ph.endPage+1 }&pageSize=${ph.pageSize }'/>" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
	    </c:if>
	  </ul>
	</nav>

	<script>
		let msg="${msg}";
		if(msg=="del") alert("성공적으로 삭제되었습니다");
		if(msg=="error") alert("삭제를 실패했습니다");
	</script>
</body>

</html>