<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file = "../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물</title>
</head>
<body>

<div class="panel panel-info" style="width:1500px;margin:auto;">
	<div class="panel-heading">
		<h3 class="panel-title">제&nbsp;&nbsp;목 : ${board.title}</h3>
		<span>작성자 : ${board.accnt_id}</span>
	</div>
	<div class="panel-body">
		${board.content}
	</div>
</div>

<div class="btn-group" role="group" aria-label="..." style="display:flex;justify-content:center;margin-top:10px;">
	<c:if test="${board.accnt_id == login.accnt_id || login.user_type == 'E' || login.user_type == 'A'}">
		<input type="button" class="btn btn-default" id="updateBtn" value="수정">
	</c:if>
	<c:if test="${board.accnt_id == login.accnt_id || login.user_type == 'A'}">
		<input type="button" class="btn btn-default" id="deleteBtn" value="삭제">
	</c:if>

	<input type="button" class="btn btn-default" value="home" onClick="location.href='/'">
	<input type="button" class="btn btn-default" value="게시물 목록" onClick="location.href='list.ja'">
	<div id="dialog-confirm" title="정말로 삭제?" style="display: none">
		<p>삭제하시면 복구할 수 없습니다. 그래도 삭제하실 건가요?</p>
	</div>
</div>



<script>
	$('#updateBtn').click(function(){
		location.href="update.ja?board_num="+${board.board_num};
	});
	
	$('#deleteBtn').click(function(){
		$("#dialog-confirm").dialog({
			resizable:false,
			height:"auto",
			width:400,
			modal:true,
			buttons:{
				"삭제":function(){
					$(this).dialog("close");
					location.href="delete.ja?board_num=${board.board_num}";
				},
				"취소":function(){
					$(this).dialog("close");
				}
			}
		});
	});
</script>
</body>
</html>