<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My TodoList</title>
<link rel="stylesheet" href="<c:url value='/css/index.css'/>">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<div id="wrap">
        <div id="wrap-center">
            <!-- 제목 -->
            <div id="header">
                <h1>My TodoList</h1>
                <h3>로그인</h3>
            </div>

            <!-- 로그인 폼 -->
            <div id="main">
                <form action="<c:url value='/login/action'/>" method="post">
                
                    <!-- 아이디 입력 -->
                    <div id="input-id">
                        <label for="id">아이디</label>
                        <input type="text" name="id" id="id" placeholder="아이디 입력" required autofocus>
                    </div>

                    <!-- 비밀번호 입력 -->
                    <div id="input-pwd">
                        <label for="pwd">비밀번호</label>
                        <input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력" required>
                    </div>

                    <!-- 아이디 기억 -->
                    <div id="input-remember-id">
                        <input type="checkbox" name="rememberId" id="remember-id">
                        <label for="remember-id">아이디 기억</label>
                    </div>

                    <!-- 버튼 -->
                    <div id="input-button">
                        <button>로그인</button>
                    </div>

                    <!-- 아이디/비밀번호 찾기, 회원가입 링크 -->
                    <div id="links">
                        <a href="#">아이디/비밀번호 찾기</a>
                        <a href="<c:url value='/register/form'/>">회원가입</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
</body>
</html>