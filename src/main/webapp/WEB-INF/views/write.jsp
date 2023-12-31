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
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <!-- 퀼 에디터 -->
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
	<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
	<script src="//cdn.quilljs.com/1.3.6/quill.min.js"></script>
	<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
</head>
<body>
	<%@include file="navi.jsp" %>
    <div id="writing-wrap">
        <form id="writing-wrap-center" action="<c:url value='/board/write'/>" method="post" onsubmit="return formCheck(this)">
        	<input type="hidden" id="content" name="content">
            <!-- 현재 페이지 설명 -->
            <div id="writing-header">
                <h3>글쓰기</h3>
            </div>
            <!-- 게시글 타입 설정(select) -->
            <div id="writing-board-type">
				<c:choose>
				
			    	<c:when test="${sessionId eq 'admin' }">
			    		<select name="boardType" id="select-writing-board-type">
			    			<option value="guide" ${mode eq 'guide' ? 'selected' : ''}>가이드</option>
				            <option value="notice" ${mode eq 'notice' ? 'selected' : ''}>공지사항</option>
				            <option value="free" ${mode eq 'free' ? 'selected' : ''}>자유게시판</option>
			    		</select>
			    	</c:when>
			    	
			    	<c:otherwise>
			    		<select name="boardType" id="select-writing-board-type">
			    			<option value="guide" disabled>가이드</option>
			    			<option value="notice" disabled>공지사항</option>
			    			<option value="free" selected>자유게시판</option>
			    		</select>
			    	</c:otherwise>
			    	
			    </c:choose>
            </div>

            <!-- 게시글 제목 -->
            <div id="writing-title">
                <input type="text" name="title" id="input-writing-title" placeholder="제목을 입력해주세요">
            </div>

            <!--게시글 내용-->
            <div id="writing-content">
            	<!--<textarea name="content" id="textarea-writing-content" placeholder="내용을 입력해주세요"></textarea>-->
            </div>
            
            <div id="editor-container"></div>

            <!--입력완료 버튼-->
            <div id="writing-button">
                <button id="button-writing-button">등록</button>
            </div>
        </form>
    </div>
    
    <%@include file="footer.jsp" %>
    
    
	<script>
		let msg = "${msg }";
		if (msg=="write_error") alert("게시글 작성에 실패했습니다");
		

		
		var quill = new Quill('#editor-container', {
            modules: {
                toolbar: [
                    [{ header: [1, 2, false] }],
                    ['bold', 'italic', 'underline'],
                    ['image', 'code-block']
                ]
            },
            placeholder: '내용을 입력하세요',
            theme: 'snow'  // or 'bubble'
        });
		
		function formCheck(frm) {
		    var quillContent = quill.root.innerHTML;
		    document.querySelector('input[name=content]').value = quillContent;

		    if (frm.title.value.trim() === "" || frm.content.value.trim() === "") {
		        alert("제목과 내용을 입력해주세요");
		        return false;
		    }
		    return true;
		}

	</script>
</body>

</html>











