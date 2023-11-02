<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>view</title>
</head>

<body>
	<form name="writeFrm">
	<h2>글 상세보기(free)</h2>
	<table class="table text-center border">
	    <thead>
	    	<tr>
	            <th>${board.writer }</th>
	            <fmt:formatDate value="${board.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="regDate" />
	            <th>${regDate }</th>
	            <th>조회 ${board.views }</th>
	            <th>추천 5</th>
	       	</tr>
	    </thead>
	    <tbody>
	        <tr>
	            <td colspan="4"><input type="text" name="title" value="${board.title }" readonly></td>
	        </tr>
	        <tr>
	            <td colspan="4"><textarea class="form-control" name="title" style="height:350px" readonly>${board.content }</textarea></td>
	        </tr>
	    </tbody>
	</table>
	</form>
	<c:choose>
		<c:when test="${sessionId eq board.writer || sessionId eq 'admin'}">
			<a href="<c:url value='/board/listFree?${searchCondition.queryString }'/>">목록</a>
			<a onclick="deletePost()">삭제</a>
			<a href="<c:url value='/board/modify?bno=${board.bno }'/>">수정</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/board/listFree?${searchCondition.queryString }'/>">목록</a>
		</c:otherwise>
	</c:choose>
	<span id="likeIcon"></span>
	<span id="likeCnt"></span>
	<%@include file="comment.jsp" %>
	
	<script>
		function deletePost(){
			var confirmed = confirm("정말 삭제하시겠습니까?");
			if(confirmed){
				var form = document.writeFrm;
				form.method = "post";
				form.action = "<c:url value='/board/remove'/>?bno=${board.bno }&page=${page }&pageSize=${pageSize }";
				form.submit();
			}
		}
		
		//좋아요 ajax 시작
		let showLike = function(bno){
			$.ajax({
				type : "POST",
				url : "/todolist/showLike?bno="+bno,
				success : function(like) {
					if (like.res == 1) {
						$("#likeIcon").html("<button id='afterLike' type='button'>💚</button>");
					} else {
						$("#likeIcon").html("<button id='beforeLike' type='button'>🤍</button>");
					}
					$("#likeCnt").html('['+ like.resAll +']');
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


























