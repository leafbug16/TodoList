<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>edit</title>
</head>

<body>
    <h2>수정</h2>
    <h2>${sessionId }, ${board.bno }</h2>
    <!-- 글수정 폼 시작 -->
    <form action="<c:url value='/board/modify?mode=${board.boardType }'/>" method="post" onsubmit="return formCheck(this)">
    	<input type="hidden" name="bno" value="${board.bno}">
	    <!-- boardType 선택 -->
	    <c:choose>
	    	<c:when test="${sessionId eq 'admin' }">
	    		<select name="boardType">
	    			<option value="guide" ${mode eq 'guide' ? 'selected' : ''}>가이드</option>
		            <option value="notice" ${mode eq 'notice' ? 'selected' : ''}>공지사항</option>
		            <option value="free" ${mode eq 'free' ? 'selected' : ''}>자유게시판</option>
	    		</select>
	    	</c:when>
	    	<c:otherwise>
	    		<select name="boardType">
	    			<option value="guide" disabled>가이드</option>
	    			<option value="notice" disabled>공지사항</option>
	    			<option value="free" selected>자유게시판</option>
	    		</select>
	    	</c:otherwise>
	    </c:choose>
	    
	    <!-- 내용 입력 부분 -->
        <table>
            <tbody>
                <tr>
                    <td><input type="text" value="${board.title }" name="title" id="title"></td>
                </tr>
                <tr>
                    <td><textarea name="content" id="content" style="height:350px">${board.content }</textarea></td>
                </tr>
            </tbody>
        </table>
        <button>수정 완료</button>
    </form>
    
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