<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My TodoList</title>
    <link rel="stylesheet" href="<c:url value='/css/edit.css'/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<%@include file="navi.jsp" %>
    <div id="edit-wrap">
        <form id="edit-wrap-center" action="<c:url value='/board/modify?mode=${board.boardType }'/>" method="post" onsubmit="return formCheck(this)">
        	<input type="hidden" name="bno" value="${board.bno}">
            <!-- 현재 페이지 설명 -->
            <div id="edit-header">
                <h3>수정</h3>
            </div>
            
            <!-- 게시글 타입 설정(select) -->
            <div id="edit-board-type">
				<c:choose>
				
			    	<c:when test="${sessionId eq 'admin' }">
			    		<select name="boardType" id="select-edit-board-type">
			    			<option value="guide" ${mode eq 'guide' ? 'selected' : ''}>가이드</option>
				            <option value="notice" ${mode eq 'notice' ? 'selected' : ''}>공지사항</option>
				            <option value="free" ${mode eq 'free' ? 'selected' : ''}>자유게시판</option>
			    		</select>
			    	</c:when>
			    	
			    	<c:otherwise>
			    		<select name="boardType" id="select-edit-board-type">
			    			<option value="guide" disabled>가이드</option>
			    			<option value="notice" disabled>공지사항</option>
			    			<option value="free" selected>자유게시판</option>
			    		</select>
			    	</c:otherwise>
			    	
			    </c:choose>
            </div>

            <!-- 게시글 제목 -->
            <div id="edit-title">
                <input type="text" name="title" id="input-edit-title" value="${board.title }">
            </div>

            <!--게시글 내용-->
            <div id="edit-content">
                <textarea name="content" id="textarea-edit-content">${board.content }</textarea>
            </div>

            <!--입력완료 버튼-->
            <div id="edit-button">
                <button id="button-edit-button">등록</button>
            </div>
        </form>
    </div>
    
	<script>
		let msg=${msg };
		if(msg=="modify_error") alert("수정 실패");
		
		function formCheck(frm) {
	      if (frm.title.value.trim() === "" || frm.content.value.trim() === "") {
	          alert("제목과 내용을 입력해주세요");
	          return false;
	      }
	      return true;
	  	}
	</script>
</body>

</html>