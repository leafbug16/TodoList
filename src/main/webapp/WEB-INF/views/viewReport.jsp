<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>문의 상세보기</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
	<form name="writeFrm">
	<h2>글 상세보기(Report)</h2>
	<table class="table text-center border">
	    <thead>
	    	<tr>
	            <th>${board.writer }</th>
	            <fmt:formatDate value="${boardDto.regDate }" type="date" pattern="yyyy-MM-dd HH:mm" var="regDate" />
	            <th>${regDate }</th>
	       	</tr>
	    </thead>
	    <tbody>
	        <tr>
	            <td colspan="2"><input type="text" name="title" value="${board.title }" readonly></td>
	        </tr>
	        <tr>
	            <td colspan="2"><textarea class="form-control" name="title" style="height:350px" readonly>${board.content }</textarea></td>
	        </tr>
	    </tbody>
	</table>
	</form>
		<a href="<c:url value='/board/listMyReport?${searchCondition.queryString }'/>">돌아가기</a>
</body>

</html>