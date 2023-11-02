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
    <h2>회원 관리</h2>
    <a href="<c:url value='/board/listAll'/>">게시판 글 관리</a>
    <a href="<c:url value='/user/listAll'/>">회원 관리</a>
    <a href="<c:url value='/board/listReported'/>">문의/bug report 관리</a>
    
    <!-- 검색폼 -->
    <form action="<c:url value='/user/listAll'/>" method="get">
      <table>
        <tr>
          <td>
            <select name="option" style="width: 130px; display: inline-block">
              <option value="W" ${ph.sc.option=='W' ? "selected" : "" }>글쓴이</option>
            </select>
            <input type="text" name="keyword" id="search"
              value='${ph.sc.keyword }' style="width: 300px; display: inline-block">
            <button>검색</button>
          </td>
        </tr>
      </table>
    </form>
    <table>
        <thead>
            <tr>
                <th>아이디</th>
                <th>비밀번호</th>
                <th>이메일</th>
                <th>가입일</th>
                <th>내쫒기</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="user" items="${list }">
            <tr>
                <td>${user.id }</td>
                <td>${user.pwd }</td>
                <td>${user.email }</td>
                <td>${user.regDate }</td>
                <td><a href="<c:url value='/user/remove?id=${user.id }&page=${page }&pageSize=${pageSize }'/>" onclick="return confirm('정말 삭제하시겠습니까?')">안녕</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- 페이지네이션 시작 --> 
	<nav>
	  <ul>
	  	<c:if test="${ph.showPrev }">
		    <li>
		      <a href="<c:url value='/user/listAll?page=${ph.beginPage-1 }&pageSize=${ph.pageSize }'/>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
	    </c:if>
	    <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
	    	<li ${ph.page==i? 'active':'' }"><a href="<c:url value='/user/listAll?page=${i }&pageSize=${ph.pageSize }' />">${i }</a></li>
	    </c:forEach>
	    <c:if test="${ph.showNext }">
		    <li>
		      <a href="<c:url value='/user/listAll?page=${ph.endPage+1 }&pageSize=${ph.pageSize }'/>" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
	    </c:if>
	  </ul>
	</nav>

	<script>
		let msg="${msg}";
		if(msg=="del") alert("보내버렸습니다");
		if(msg=="error") alert("손절을 실패했습니다");
	</script>
</body>

</html>