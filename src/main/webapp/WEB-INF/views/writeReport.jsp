<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>문의 / bug report</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
    <h2>문의 / bug report</h2>
    <h2>${sessionId }</h2>
    <!-- 글쓰기 폼 시작 -->
    <form action="<c:url value='/board/write'/>" method="post" onsubmit="return formCheck(this)">
    	<input type="hidden" name="boardType" value="report">
	    
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