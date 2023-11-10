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
    <a href="<c:url value='/board/writeReport'/>">문의 / bug report</a>
    
    <button type="button" id="addBtn">추가</button>
    <button type="button" id="deleteAllBtn">전체 삭제</button>
    
    <!-- 목록 가져오기 ajax -->
    <div id="lists-div"></div>
     
    <script>
    	const id = "${sessionId}";
    	const showList = function(id) {
    		$.ajax({
    			type : "GET",
    			url : "/todolist/todolist/lists",
    			success : function(res) {
    				$("#lists-div").html(toHtmlLists(res));
    			},
    			error : function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"목록 조회 중 에러") }
    		});//ajax
    	}//showList
    	
    	const toHtmlLists = function(lists) {
    		let tmp = "<ul id='list-ul'>";
    		lists.forEach(function(list){
    			tmp += "<li id='list-li'>";
	    			tmp += "<div id='list-div' data-lno="+list.lno+">";
	    				tmp += "<a href='<c:url value='/todolist/read?lno="+list.lno+"'/>'>"+list.title+"</a>";
	    				tmp += "<button type='button' class='listmodBtnb'>수정</button>";
	    				tmp += "<button type='button' class='listdelBtn'>삭제</button>";
    			tmp += "</div></li>"
    		})//forEach
    		return tmp + "</ul>";
    	}//toHtml
    	
    	$(document).ready(function(){
    		showList(id);
    		
    		$("#addBtn").click(function(){
    			$.ajax({
    				type : "POST",
    				url : "/todolist/todolist/lists",
    				success : function(res) {
    					showList(id);
    				},
    				error : function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"목록 추가 중 에러") }
    			});//ajax
    		});//addBtn click
    		
    		//수정1(목록)
    		$("#lists-div").on("click", ".listmodBtnb", function(){
    			const lno = $(this).parent().attr("data-lno");
    			const origin = $("a", $(this).parent()).html().trim();
    			
    			$(this).parent().replaceWith("<div id='list-modify-div'><textarea name='retitle' id='retitle'>"+origin+"</textarea><button type='button' id='listmodBtn'>수정완료</button></div>");
    			$("#listmodBtn").attr("data-lno", lno);
    		});//listmodBtnb click
    		
    		//수정2(목록)
    		$("#lists-div").on("click", "#listmodBtn", function(){
    			const title = $("textarea[name=retitle]").val();
    			const lno = $("#listmodBtn").attr("data-lno");
    			if(title.trim() == ""){
    				alert("내용을 입력하세요");
    				return;
    			}
    			$.ajax({
    				type : "PATCH",
    				url : "/todolist/todolist/lists",
    				headers : {"content-type" : "application/json"},
  					data : JSON.stringify({ lno: lno, title: title}),
  					success : function(res){
  						showList(id);
  					},
  					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"목록 수정 완료 중 에러") }
    			});//ajax
    		});//listmodBtn click
    		
    		//할일 목록 삭제
    		$("#lists-div").on("click", ".listdelBtn", (function(){
    			const lno = $(this).parent().attr("data-lno");
    			$.ajax({
    				type : "DELETE",
    				url : "/todolist/todolist/lists?lno="+lno,
    				success : function(res) {
    					showList(id);
    				},
    				error : function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"목록 삭제 중 에러") }
    			});//ajax
    		}));//delBtn click
    		
    		//할일 목록 전체 삭제
    		$("#deleteAllBtn").click(function(){
    			$.ajax({
    				type : "DELETE",
    				url : "/todolist/todolist/listsAll",
    				success : function(res) {
    					showList(id);
    				},
    				error : function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"목록 삭제 중 에러") }
    			});//ajax
    		});
    		
    	});//ready
    
    
    </script>
     
</body>
</html>

































