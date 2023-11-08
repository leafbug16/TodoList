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
    <link rel="stylesheet" href="<c:url value='/css/myReport.css'/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<%@include file="navi.jsp" %>
    <div id="myreport-wrap">
        <div id="myreport-wrap-center">

            <div id="myreport-info">
                <h3>마이페이지 - 내 문의/bug report</h3>
            </div>

            <div id="myreport-userinfo">
                <h1>admin</h1>
            </div>

            <div id="myreport-nav">
                <ul>
                    <li><a href="<c:url value='/board/listMyLike'/>">좋아요한 글</a></li>
                    <li><a href="<c:url value='/board/listMyPost'/>">작성글</a></li>
                    <li><a href="<c:url value='/board/listMyComment'/>">작성댓글</a></li>
                    <li><a href="<c:url value='/board/listMyReport'/>">문의/bug report 내역</a></li>
                </ul>
                <a href="<c:url value='/user/remove?id=${sessionId }&mode=selfRemove'/>" onclick="return confirm('정말 탈퇴하시겠습니까?')" id="myreport-delete-user">회원탈퇴</a>
                <hr>
            </div>

            <div id="myreport-board">
                <table id="myreport-board-table">
                    <thead>
                        <tr id="myreport-first-tr">
                            <th>카테고리</th>
                            <th>제목</th>
                            <th>날짜</th>
                        </tr>
                    </thead>

                    <tbody>
                    	<c:forEach var="board" items="${list }">
				            <tr>
				                <td>${board.boardType }</td>
				                <td><a href="<c:url value='/board/read?bno=${board.bno }&mode=${board.boardType }'/>">${board.title }</a></td>
				                <fmt:formatDate value="${board.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="reg_date" />
				                <td>${reg_date }</td>
				            </tr>
			            </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- 페이지네이션 -->
            <div id="myreport-pagenation">
                <ul>
				  	<c:if test="${ph.showPrev }">
						<li>
							<a href="<c:url value='/board/listMyReport?page=${ph.beginPage-1 }&pageSize=${ph.pageSize }'/>" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					      	</a>
					    </li>
				    </c:if>
				    <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
				    	<li><a class="${ph.page==i? 'active':'' }" href="<c:url value='/board/listMyReport?page=${i }&pageSize=${ph.pageSize }' />">${i }</a></li>
				    </c:forEach>
				    <c:if test="${ph.showNext }">
						<li>
					    	<a href="<c:url value='/board/listMyReport?page=${ph.endPage+1 }&pageSize=${ph.pageSize }'/>" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      	</a>
					    </li>
				    </c:if>
			  	</ul>
            </div>

        </div>
    </div>
    
    <%@include file="footer.jsp" %>

	<script>
		let msg="${msg}";
		if(msg=="write_ok") alert("성공적으로 등록되었습니다");
	</script>
</body>

</html>