<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Todolist</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
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
    <a href="<c:url value='/board/writeReport'/>">문의 / bug report</a><br>
    
    <!-- 목록 가져오기 테스트 -->
    <a href="<c:url value='/todolist/write?id=${sessionId }'/>">추가</a>
    <ul>
    	<c:forEach var="list" items="${lists }">
    	
    		<li><a href="<c:url value='/todolist/read?lno=${list.lno }'/>">${list.title }</a></li>
    		
    		<form action="<c:url value='/todolist/modify'/>" method="get">
    			<input type="hidden" name="lno" value="${list.lno }">
    			<input type="text" name="title" placeholder="목록 이름 수정">
	    		<button>수정</button>
    		</form>
    		
    		<a href="<c:url value='/todolist/remove?lno=${list.lno }'/>">삭제</a>
    		
    	</c:forEach>
    </ul>
    
    <h1>view화면</h1>
    <h2>lno = ${tl.lno }</h2>
    
    <!-- 할일 목록들 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
    <div id="todoList"></div>
    
    <div id="todo-input">
    	<textarea name="content" id="content" placeholder="할일 작성"></textarea>
    	<button type="button" id="sendBtn">등록</button>
    </div>
    
    <script>
    	const lno = ${tl.lno};
    	const showList = function(lno) {
    		$("textarea[name=content]").val("");
    		$.ajax({
    			type : "GET",
    			url : "/todolist/todolist/todos?lno="+lno,
		        success : function(res) {
		        	$("#todoList").html(toHtml(res));
		        },
		        error : function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"조회중에러") }
    		});//ajax
    	}//showList
    	
    	const toHtml = function(todoLists) {
    		let tmp = "<ul id='todo-ul'>";
    		todoLists.forEach(function(todo){
    			tmp += "<li id='todo-li'>";
    			tmp += "<div id='todo-div' data-tno="+todo.tno+" data-lno="+todo.lno+">";
    			tmp += "<input type='checkbox' id='todo-checkbox'>";
    			tmp += "<label for='todo-checkbox'>"+todo.content+"</label>";
    			tmp += "<button type='button' class='modBtnb'>수정</button>";
    			tmp += "<button type='button' class='delBtn'>삭제</button>";
    			tmp += "</div></li>"
    		})//forEach
    		return tmp + "</ul>";
    	}//toHtml
    	
    	$(document).ready(function(){
    		showList(lno);
    		
    		//할일 추가
    		$("#sendBtn").click(function(){
    			const content = $("textarea[name=content]").val();
    			if(content.trim() == "") {
    				alert("내용을 입력하세요");
    				return;
    			}//if
    			$.ajax({
    				type : "POST",
    				url : "/todolist/todolist/todos",
    				headers : {"content-type" : "application/json"},
  					data : JSON.stringify({ lno: lno, content: content}),
  					success : function(res) {
  						showList(lno);
  					},
  					error : function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"등록 중 에러") }
    			});//ajax
    		})//sendBtn click
    		
    		
    		
    	});//ready
    
    </script>
    
</body>
</html>






































