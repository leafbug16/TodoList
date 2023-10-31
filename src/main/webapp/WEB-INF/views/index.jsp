<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form action="<c:url value='/login/action'/>" method="post">
		<input type="text" name="id" id="id" placeholder="아이디 입력"><br>
		<input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력"><br>
		<input type="checkbox" name="keepLogin" id="keepLogin"> 로그인 유지<br>
		<a href="<c:url value='/register/form'/>">회원가입</a>
		<button>로그인</button>
	</form>
</body>
</html>