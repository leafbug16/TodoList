<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My TodoList</title>
    <link rel="stylesheet" href="<c:url value='/css/write.css'/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<%@include file="navi.jsp" %>
    <div id="writing-wrap">
        <form id="writing-wrap-center" action="<c:url value='/board/write'/>" method="post" onsubmit="return formCheck(this)">
            <!-- 현재 페이지 설명 -->
            <div id="writing-header">
                <h3>문의/bug report</h3>
            </div>
            <!-- 히든 인풋 -->
            <input type="hidden" name="boardType" value="report">

            <!-- 게시글 제목 -->
            <div id="writing-title">
                <input type="text" name="title" id="input-writing-title" placeholder="제목을 입력해주세요">
            </div>

            <!--게시글 내용-->
            <div id="writing-content">
                <textarea name="content" id="textarea-writing-content" placeholder="내용을 입력해주세요"></textarea>
            </div>

            <!--입력완료 버튼-->
            <div id="writing-button">
                <button id="button-writing-button">등록</button>
            </div>
        </form>
    </div>
    
    <%@include file="footer.jsp" %>
    
	<script>
		let msg = ${msg };
		if (msg=="write_error") alert("작성에 실패했습니다");
		
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