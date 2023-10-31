<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/register/action'/>" method="post">
		<input required type="text" name="id" id="id" placeholder="아이디 입력"><br>
		<input required type="password" name="pwd" id="pwd" placeholder="비밀번호 입력"><br>
		<input type="password" name="pwdCheck" id="pwdCheck" placeholder="비밀번호 확인"><br>
		<input type="email" name="email" id="email" placeholder="이메일 입력(선택)"><br>
		<button>회원가입</button>
	</form>
</body>
</html>