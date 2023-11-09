<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My TodoList</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/77d5171cb8.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<c:url value='/css/comment.css'/>">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>


<body>
	<div id="comment-wrap">
        <div id="comment-wrap-center">

            <div id="comment-header">
                <h3 id=comment-header-text>댓글</h3>
                <hr/>
            </div>

            <div id="commentList">
            </div>

            <div id="comment-input">
                <textarea name="comment" id="comment" placeholder="댓글을 작성해보세요"></textarea>
                <button type="button" id="sendBtn">등록</button>
            </div>

        </div>
    </div>
	
	<script>
		let bno = ${board.bno };
		let mode = false;
		let showList = function(bno){
			$('textarea[name=comment]').val("");
			$.ajax({
				type : "GET",
				url : "/todolist/comments?bno="+bno,
				success : function(result) {
					$("#commentList").html(toHtml(result));
				},
				error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"조회중에러") }
			});
		}
		
		let toHtml = function(comments) {
			let tmp = "<ul id='comment-ul'>";
			comments.forEach(function (comment) {
				tmp += "<li id='comment-li' data-cno="+comment.cno + " data-bno="+comment.bno + ">";
				tmp += "<div id='comment-div' data-cno="+comment.cno + " data-bno="+comment.bno + ">";
				tmp +='<span id="commentlist-commenter"><b>'+comment.commenter+'</b></span><br>';
				tmp +='<span id="commentlist-comment"> '+comment.comment.replace(/\n/g, '<br>')+'</span>';
				if (comment.commenter == "${sessionScope.id }" || "admin == ${sessionScope.id}") {
					tmp += "<button type='button' class='modBtnb'><i class='fa-solid fa-pen'></i></button>";
					tmp += "<button type='button' class='delBtn'><i class='fa-solid fa-x'></i></button>";
				}
				tmp +="</div>";
				tmp += "</li>";
				tmp += "<hr id='comment-hr'>";
			})
			return tmp + "</ul>";
		}
		
		$(document).ready(function() {
			showList(bno);
			//등록 버튼
			$("#sendBtn").click(function() {
				let comment = $("textarea[name=comment]").val();
				if (comment.trim() == "") {
					alert("내용을 입력하세요");
					return;
				}
				$.ajax({
					type : "POST",
					url : "/todolist/comments?bno="+bno,
					headers : {"content-type" : "application/json"},
					data : JSON.stringify({ bno: bno, comment: comment}),
					success : function(result) {
						showList(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
				}); //ajax
			}); //sendBtn
			
			//댓글 옆 수정버튼 클릭 시
			$("#commentList").on("click", ".modBtnb", function() {
				//부모는 comment-li
			    let cno = $(this).parent().attr("data-cno");
			    let bno = $(this).parent().attr("data-bno");                
			    let originalComment = $("span#commentlist-comment", $(this).parent()).html().replace('<br>', '\r\n').trim();
			    console.log(originalComment)
			    
			    // 대체
			    //$("span#comment", $(this).parent()).replaceWith("<textarea name='recomment' id='recomment" + cno + "' style='width: 500px; height:80px;'>"+originalComment+"</textarea>");
			    //$(this).replaceWith("<button type='button' id='modBtn'>수정</button>");
			    
			    $(this).parent().replaceWith("<div id='comment-modify'><textarea name='recomment' id='recomment" + cno + "'>"+originalComment+"</textarea><button type='button' id='modBtn'>수정</button></div>");
			    //$(this).replaceWith("<button type='button' id='modBtn'>수정</button>");
			    	    
			    $("#modBtn").attr("data-cno", cno);
			}); 
			
			//수정완료 클릭 시
			$("#commentList").on("click", "#modBtn", function() {
				let comment = $("textarea[name=recomment]").val();
				if (comment.trim() == "") {
					alert("내용을 입력하세요");
					return;
				}
				let cno = $("#modBtn").attr("data-cno");

				$.ajax({
					type : "PATCH",
					url : "/todolist/comments/"+cno,
					headers : {"content-type" : "application/json" },
					data : JSON.stringify({ cno: cno, comment: comment }),
					success : function(result){
						showList(bno);
			        },
			        error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
			     }); //ajax

			     // 복구
			     $("textarea[name=recomment]", $(this).parent()).replaceWith('<span class="comment"> '+ comment +'</span>');
			     $(this).replaceWith("<button type='button' class='modBtnb'>수정</button>");
			});
			
			//삭제 버튼
			$("#commentList").on("click", ".delBtn", (function() {
				let cno = $(this).parent().attr("data-cno");
				let bno = $(this).parent().attr("data-bno");
				$.ajax({
					type : "DELETE",
					url : "/todolist/comments/"+cno+"?bno="+bno,
					success : function(result) {
						showList(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
				}); //ajax
			})); //delBtn	
		}); //ready
	</script>
</body>
</html>











