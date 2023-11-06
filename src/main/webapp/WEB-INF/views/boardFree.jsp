<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="kr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My TodoList</title>
    <link rel="stylesheet" href="<c:url value='/css/board.css'/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>

<body>
	<%@include file="navi.jsp" %>
	<div id="board-wrap">
        <div id="board-wrap-center">
            <!-- 게시판 설명 -->
            <div id="board-info">
                <h3>자유게시판</h3>
            </div>

            <!-- 게시판 -->
            <div id="main">
                <div id="board">
                    <table id="board-table">
                        <thead>
                            <tr id="first-tr">
                                <th>번호</th>
                                <th>제목</th>
                                <th>글쓴이</th>
                                <th>등록일</th>
                                <th>조회</th>
                                <th>좋아요</th>
                            </tr>
                        </thead>

                        <tbody>
                        	<c:forEach var="board" items="${list }">
	                            <tr>
	                                <td>${board.bno }</td>
	                                <td><a href="<c:url value='/board/read?bno=${board.bno }&${ph.sc.queryString }&mode=free'/>">${board.title }</a></td>
	                                <td>${board.writer }</td>
	                                <fmt:formatDate value="${board.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="regDate" />
	                				<td>${regDate }</td>
	                                <td>${board.views }</td>
	                                <td>${board.likes }</td>
	                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- 글쓰기 버튼 -->
            <div id="write-button">
                <button type="button" onclick="location.href='<c:url value='/board/write?mode=free'/>'">글쓰기</button>
            </div>

            <!-- 페이지네이션 -->
            <div id="pagenation">
                <ul>
				  	<c:if test="${ph.showPrev }">
					    <li>
					      <a href="<c:url value='/board/listFree${ph.sc.getQueryString(ph.beginPage-1) }'/>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:if>
				    <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				    	<li><a class=" ${ph.sc.page==i? 'active':'' }" href="<c:url value='/board/listFree${ph.sc.getQueryString(i) }' />">${i }</a></li>
				    </c:forEach>
				    <c:if test="${ph.showNext }">
					    <li>
					      <a href="<c:url value='/board/listFree${ph.sc.getQueryString(ph.endPage+1) }'/>" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:if>
				</ul>
            </div>

            <!-- 검색폼 -->
            <div id="search-area">
                <form action="<c:url value='/board/listFree'/>" method="get">
                    <div id="search-area-flex">
                        
                        <!-- 셀렉트 -->
                        <div id="select">
                            <select name="option">
                                <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : "" }>제목+내용</option>
                                <option value="T" ${ph.sc.option=='T' ? "selected" : "" }>제목</option>
                                <option value="W" ${ph.sc.option=='W' ? "selected" : "" }>글쓴이</option>
                            </select>
                        </div>

                        <div id="search">
                            <input type="text" name="keyword" value="${ph.sc.keyword }" placeholder="검색어를 입력하세요">
                            <button>검색</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
    
    <%@include file="footer.jsp" %>

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