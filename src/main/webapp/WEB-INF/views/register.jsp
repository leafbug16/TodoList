<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My TodoList</title>
    <link rel="stylesheet" href="<c:url value='/css/register.css'/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    <div id="wrap">
        <div id="wrap-center">
            <!-- 제목 -->
            <div id="header">
                <h1>My TodoList</h1>
                <h3>회원가입</h3>
            </div>

            <!-- 회원가입 폼 -->
            <div id="main">
                <form id="register-form" action="<c:url value='/register/action'/>" method="post">
                	
                	<!--아이디 중복체크 + 유효성검사 메시지-->
                    <div id="duplicate-id-check-div">
                        <span class="" id="duplicate-id-check"></span>
                    </div>
                    
                    <!-- 아이디 입력 -->
                    <div id="input-id">
                    	<span id="duplicate-id-check"></span>
                        <label for="id">아이디</label>
                        <input type="text" name="id" id="id" placeholder="아이디 입력" required autofocus>
                    </div>
                    
                    <!-- 비밀번호 유효성 검사 메시지 -->
                    <div id="pwd-div">
                        <span class="" id="pwd-msg"></span>
                    </div>

                    <!-- 비밀번호 입력 -->
                    <div id="input-pwd">
                        <label for="pwd">비밀번호</label>
                        <input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력" required>
                    </div>
					
					<!-- 비밀번호 확인 메시지 -->
                    <div id="pwd-check-div">
                        <span class="" id="pwd-check-msg"></span>
                    </div>
                    
                    <!-- 비밀번호 확인 -->
                    <div id="input-pwd-check">
                        <label for="pwd">비밀번호 확인</label>
                        <input type="password" name="pwdCheck" id="pwdCheck" placeholder="비밀번호 확인" required>
                    </div>

                    <!-- 이메일 입력 -->
                    <div id="input-email">
                        <label for="email">이메일</label>
                        <input type="email" name="email" id="email" placeholder="이메일 입력">
                    </div>
                    <div id="email-info">
                        <span id="email-info-text">
                            이메일은 아이디/비밀번호를 찾는 용도로만 사용됩니다.<br/>
                            필수 입력 사항이 아닙니다.
                        </span>
                    </div>

                    <!-- 버튼 -->
                    <div id="input-button">
                        <button>회원가입</button>
                    </div>
                    
                    <!-- 로그인으로 돌아가기 -->
                    <div id="login-link-div">
                        <a href="<c:url value='/'/>">로그인으로 돌아가기</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
    
    <script>
    	const pwd = document.querySelector("#pwd");
    	const pwdCheck = document.querySelector("#pwdCheck")	
    	const registerForm = document.querySelector("#register-form");
    	const id = document.querySelector("#id");

	    registerForm.addEventListener("submit", function(event) {
	        if(pwd.value !== pwdCheck.value) {
	            alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
	            event.preventDefault();
	        }
	        if(!$("#duplicate-id-check").hasClass("checked")) {
	          alert("아이디를 제대로 입력해주세요.");
	          event.preventDefault();
	      	}
	        if(!$("#pwd-msg").hasClass("checked")) {
	          alert("비밀번호를 제대로 입력해주세요");
	          event.preventDefault();
	      	}
	    });
	    
	    
	    
	    $(document).ready(function(){
		    $('#id').keyup(function(){
		  		let id = $("input[name=id]").val();
		  		console.log(id);
		  		if (id == "" || id.length < 2 || id.length > 15) {
					$("#duplicate-id-check").html("아이디는 2~15글자로 작성해주세요");
					$("#duplicate-id-check").removeClass("checked");
					return;
		  		}
		  		$.ajax({
		  			type: "GET",
		  			url: "/todolist/register/duplicateIdCheck?id="+id,
		  			success: function(check) {
	  					if (check.res == 1) {
	  						$("#duplicate-id-check").html("중복된 아이디가 존재합니다");
	  						$("#duplicate-id-check").removeClass("checked");
	  					} else {
	  						$("#duplicate-id-check").html("사용 가능합니다");
	  						$("#duplicate-id-check").addClass("checked");
	  					}
		  			}, //success
		  			error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"idCheck 중 에러") }
		  		}); //ajax
		  	});
		    
		    pwdCheck.addEventListener('keyup', function() {
			    if(pwd.value !== pwdCheck.value) {
			    	$("#pwd-check-msg").html("비밀번호가 일치하지 않습니다.");
			    	$("#pwd-check-msg").removeClass("checked");
			    } else {
			    	$("#pwd-check-msg").html("비밀번호 일치");
			    	$("#pwd-check-msg").addClass("checked");
			    }
		  	});
		    
		    pwd.addEventListener('keyup', function() {
			    if(pwd.value == "" || pwd.value.length < 2 || pwd.value.length > 20) {
			    	$("#pwd-msg").html("비밀번호는 2~20글자로 작성해주세요");
			    	$("#pwd-msg").removeClass("checked");
			    } else {
			    	$("#pwd-msg").html("사용 가능합니다");
			    	$("#pwd-msg").addClass("checked");
			    }
		  	});
		        
		    
	    });
    </script>
</body>
</html>






































