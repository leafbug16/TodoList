<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>게시판 테스트</title>
    <style>
    	#title {
    		display: inline-block;
    	}
    </style>
</head>

<body>
    <h2 id="title">가이드</h2>    
    <a href="<c:url value='/'/>">할일로 돌아가기</a><br>
    <a href="<c:url value='/board/listGuide'/>">가이드</a>
    <a href="<c:url value='/board/listNotice'/>">공지사항</a>
    <a href="<c:url value='/board/listFree'/>">자유게시판</a>
    <!-- 검색폼 -->
    <form action="<c:url value='/board/listGuide'/>" method="get">
      <table>
        <tr>
          <td>
            <select name="option" style="width: 130px; display: inline-block">
              <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : "" }>제목+내용</option>
              <option value="T" ${ph.sc.option=='T' ? "selected" : "" }>제목</option>
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
                <td><a href="<c:url value='/board/read?bno=${board.bno }&${ph.sc.queryString }&mode=guide'/>">${board.title }</a></td>
                <td>${board.writer }</td>
                <fmt:formatDate value="${board.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="reg_date" />
                <td>${reg_date }</td>
                <td>${board.views }</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- 페이지네이션 시작 --> 
    <c:if test="${sessionId eq 'admin' }">
		<a href="<c:url value='/board/write'/>">글쓰기</a>    
    </c:if>
	<nav>
	  <ul>
	  	<c:if test="${ph.showPrev }">
		    <li class="page-item">
		      <a class="page-link" href="<c:url value='/board/listGuide${ph.sc.getQueryString(ph.beginPage-1) }'/>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
	    </c:if>
	    <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
	    	<li class="page-item ${ph.sc.page==i? 'active':'' }"><a class="page-link" href="<c:url value='/board/listGuide${ph.sc.getQueryString(i) }' />">${i }</a></li>
	    </c:forEach>
	    <c:if test="${ph.showNext }">
		    <li class="page-item">
		      <a class="page-link" href="<c:url value='/board/listGuide${ph.sc.getQueryString(ph.endPage+1) }'/>" aria-label="Next">
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