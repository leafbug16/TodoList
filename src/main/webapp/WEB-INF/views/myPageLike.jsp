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
    <h2>추천한 글</h2>
    <a href="<c:url value='/board/listMyLike'/>">추천한 글</a>
    <a href="<c:url value='/board/listMyPost'/>">작성한 글</a>
    <a href="<c:url value='/board/listMyComment'/>">작성한 댓글</a>
    <a href="<c:url value='/board/listMyReport'/>">문의 내역</a>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>등록일</th>
                <th>조회</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="board" items="${list }">
            <tr>
                <td>${board.bno }</td>
                <td><a href="<c:url value='/board/read?bno=${board.bno }&mode=myPageLike'/>">${board.title }</a></td>
                <td>${board.writer }</td>
                <fmt:formatDate value="${board.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="reg_date" />
                <td>${reg_date }</td>
                <td>${board.views }</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- 페이지네이션 시작 --> 
	<nav>
	  <ul>
	  	<c:if test="${ph.showPrev }">
		    <li>
		      <a href="<c:url value='/board/listLike?page=${ph.beginPage-1 }&pageSize=${ph.pageSize }'/>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
	    </c:if>
	    <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
	    	<li ${ph.page==i? 'active':'' }"><a href="<c:url value='/board/listLike?page=${i }&pageSize=${ph.pageSize }' />">${i }</a></li>
	    </c:forEach>
	    <c:if test="${ph.showNext }">
		    <li>
		      <a href="<c:url value='/board/listLike?page=${ph.endPage+1 }&pageSize=${ph.pageSize }'/>" aria-label="Next">
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
		if(msg=="write_ok") alert("성공적으로 등록되었습니다");
		if(msg=="modify_error") alert("작성자만 수정할 수 있습니다");
		if(msg=="modify_ok") alert("수정 성공");
	</script>
</body>

</html>