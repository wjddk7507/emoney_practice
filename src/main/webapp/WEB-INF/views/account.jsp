<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
th{
color:#00d2b5;
text-align:center;
}
</style>
<body>

		<table id="accnt_info" class="table table-hover" style="width:1500px;margin:auto;text-align:center">
				<tr>
					<th onclick="event.cancelBubble=true" width="5%">ACCNT_ID</th>
					<th onclick="event.cancelBubble=true" width="15%">ID</th>
					<th onclick="event.cancelBubble=true" width="10%">NICKNAME</th>
					<th onclick="event.cancelBubble=true" width="10%">NAME</th>
					<th onclick="event.cancelBubble=true" width="5%">USER_TYPE</th>
					<th onclick="event.cancelBubble=true" width="10%">PHONE</th>
					<th onclick="event.cancelBubble=true" width="30%">S_PASSWD</th>
					<th onclick="event.cancelBubble=true" width="15%">last_login</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr style="cursor:pointer">
						<td>${vo.accnt_id}</td>
						<td>${vo.id}</td>
						<td>${vo.nickname}</td>
						<td>${vo.name}</td>
						<td>${vo.user_type}</td>
						<td>${vo.phone}</td>
						<td>${vo.s_passwd}</td>
						<td>${vo.last_login}</td>
					</tr>
					
				</c:forEach>
			</table>
			<div id="dialog-confirm" title="회원정보 수정" style="display: none">
				<p>수정하시겠습니까?</p>
			</div>
		<div style="text-align:center">
			<input type="button" class="btn btn-default" value="home" onClick="location.href='/'">
		</div>
</body>
<script>

if("${login.user_type}"=="A") {
	$("#accnt_info tr").click(function(){
		var td = $(this).children();
		var accnt_id = td.eq(0).text();
			$("#dialog-confirm").dialog({
				resizable:false,
				height:"auto",
				width:400,
				modal:true,
				buttons:{
					"수정":function(){
						$(this).dialog("close");
						location.href="user_typeUpdate.ja?accnt_id="+accnt_id;
					},
					"취소":function(){
						$(this).dialog("close");
					}
				}
			});
	});
}
</script>
</html>