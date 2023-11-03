<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>write</title>
</head>

<body>
    <h2>글쓰기</h2>
    <h2>${sessionId }</h2>
    <!-- 글쓰기 폼 시작 -->
    <form action="<c:url value='/board/write'/>" method="post" onsubmit="return formCheck(this)">
    
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
                    <td><input type="text" placeholder="글 제목" name="title" id="title"></td>
                </tr>
                <tr>
                    <td><textarea placeholder="글 내용" name="content" id="content" style="height:350px"></textarea></td>
                </tr>
            </tbody>
        </table>
        <button>작성 완료</button>
    </form>
    
	<script>
		let msg = ${msg };
		if (msg=="write_error") alert("게시글 작성에 실패했습니다");
		
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