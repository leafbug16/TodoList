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
    <link rel="stylesheet" href="<c:url value='/css/view.css'/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>

<body>
	<%@include file="navi.jsp" %>
	<div id="view-wrap">
        <div id="view-wrap-center">
            <!-- 게시판 종류와 수정, 삭제, 목록 버튼 -->
            <div id="view-nav">
                <h3>자유게시판</h3>
                <c:choose>
					<c:when test="${sessionId eq board.writer || sessionId eq 'admin'}">
						
					</c:when>
					
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>
                <div id="view-nav-button">
                    <button type="button" onclick="location.href='<c:url value='/board/modify?bno=${board.bno }&mode=free'/>'">수정</button>
                    <button type="button"><a href="<c:url value='/board/remove?bno=${board.bno }&page=${page }&pageSize=${pageSize }&mode=free'/>"
                    onclick="return confirm('정말 삭제하시겠습니까?')">삭제</a></button>
                    <button type="button" onclick="location.href='<c:url value='/board/listFree?${searchCondition.queryString }'/>'">목록</button>
                </div>
            </div>

            <!-- 글 제목 -->
            <div id="post-title">
                <h1>${board.title }</h1>
            </div>

            <!-- 글 정보 -->
            <fmt:formatDate value="${board.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="regDate" />
            <div id="post-info">
                <span>${board.writer }&nbsp;&nbsp;|&nbsp;&nbsp;${regDate } &nbsp;&nbsp;조회&nbsp;${board.views }</span>
            </div>

            <!-- 본문 -->
            <div id="post-content">
                <span>
                	${board.content }
                </span>
            </div>

		    <!-- 좋아요 버튼, 숫자 -->
		    <div id="post-like">
		        <span id="likeIcon"></span>
		     <span id="likeCnt"></span>
		    </div>
        </div>
    </div>
    <!-- 댓글은 파일을 따로 만들어서 include -->
	<%@include file="comment.jsp" %>
    
    <!-- 뷰와 새로운 게시판의 구분선 -->
    <div id="post-spacing">
    	<hr/ style="width: 1280px;">
    </div>

    <!-- view에 해당하는 게시판 include -->
    

    <!-- footer include -->
    <%@include file="footer.jsp" %>
	
	
	
	<script>		
		//좋아요 ajax 시작
		let showLike = function(bno){
			$.ajax({
				type : "POST",
				url : "/todolist/showLike?bno="+bno,
				success : function(like) {
					if (like.res == 1) {
						$("#likeIcon").html("<button id='afterLike' type='button'>💗</button>");
					} else {
						$("#likeIcon").html("<button id='beforeLike' type='button'>🤍</button>");
					}
					$("#likeCnt").html('좋아요['+ like.resAll +']');
				},
				error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"좋아요 정보 조회 중 에러") }
			});
		}
		
		//로드 시
		$(document).ready(function(){
			showLike(bno);
			$('#likeIcon').on("click", "#afterLike", function() {
				$.ajax({
					type : "POST",
					url : "/todolist/removeLike?bno="+bno,
					success : function(result) {
						showLike(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"\n" +"좋아요 해제 에러") }
				}); //ajax
			}); //afterLike
			
			$('#likeIcon').on("click", "#beforeLike", function() {
				$.ajax({
					type : "POST",
					url : "/todolist/addLike?bno="+bno,
					success : function(result) {
						showLike(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"\n" +"좋아요 에러") }
				}); //ajax
			}); //beforeLike	
		});	
		
		
	</script>
</body>

</html>


























