<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <style>
    	#title {
    		display: inline-block;
    	}
    </style>
</head>

<body>
    <h2 id="title">마이페이지</h2>    
    <a href="<c:url value='/'/>">할일로 돌아가기</a><br>
    <h2>문의/bug report 내역</h2>
    <a href="<c:url value='/board/listMyLike'/>">추천한 글</a>
    <a href="<c:url value='/board/listMyPost'/>">작성한 글</a>
    <a href="<c:url value='/board/listMyComment'/>">작성한 댓글</a>
    <a href="<c:url value='/board/listMyReport'/>">문의/bug report 내역</a>
    <table>
        <thead>
            <tr>
            	<th>카테고리</th>
                <th>제목</th>
                <th>보낸 날짜</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="board" items="${list }">
            <tr>
                <td>${board.boardType }</td>
                <td><a href="<c:url value='/board/read?bno?bno=${board.bno }&mode=myReport'/>">${board.title }</a></td>
                <fmt:formatDate value="${board.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="reg_date" />
                <td>${reg_date }</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
	<a href="<c:url value='/board/writeReport'/>">문의/bug report</a>    
    <!-- 페이지네이션 시작 --> 
	<nav>
	  <ul>
	  	<c:if test="${ph.showPrev }">
		    <li>
		      <a href="<c:url value='/board/listMyReport?page=${ph.beginPage-1 }&pageSize=${ph.pageSize }'/>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
	    </c:if>
	    <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
	    	<li ${ph.page==i? 'active':'' }"><a href="<c:url value='/board/listMyReport?page=${i }&pageSize=${ph.pageSize }' />">${i }</a></li>
	    </c:forEach>
	    <c:if test="${ph.showNext }">
		    <li>
		      <a href="<c:url value='/board/listMyReport?page=${ph.endPage+1 }&pageSize=${ph.pageSize }'/>" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
	    </c:if>
	  </ul>
	</nav>

	<script>
		let msg="${msg}";
		if(msg=="write_ok") alert("성공적으로 등록되었습니다");
	</script>
</body>

</html>