<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file = "../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>

</head>
<style>
	table {
		width:500px;
		margin:auto;
		border-collapse: collapse;
		text-align:center;
	}
	input {
		width:500px;
		height:30px;
	}
	td {
		padding:1px;
	}
</style>

<body>

	<form accept-charset="UTF-8" method="post" onsubmit="return check()">
		<table>
			<tr>
				<th style="padding:10px">로그인</th>
			</tr>
			<tr>
				<td>
					<input type="text" name="id" id="id">
				</td>
			</tr>
			<tr>
				<td>
					<input type="password" name="s_passwd" id="s_passwd">
				</td>
			</tr>
			<tr>
				<td style="padding:10px">
					<input type="submit" class="btn btn-default" value="로그인">
				</td>
			</tr>
		</table>
	</form>
	
<!-- 네이버 로그인 화면으로 이동 시키는 URL -->
<!-- 네이버 로그인 화면에서 ID, PW를 올바르게 입력하면 callback 메소드 실행 요청 -->
<div id="naver_id_login" style="text-align:center">
<a href="${url}"><img width="223" src="/resources/img/naver.PNG"/></a>
</div>
<div style="text-align:center;margin-top:20px">
		<a href="join.ja">회원가입</a>
</div>
</body>
<script>

	function check() {
		var id = document.getElementById("id");
		var s_passwd = document.getElementById("s_passwd");
		
		if(id.value == "" || id.value == null) {
			alert("id를 입력해 주세요");
			id.focus();
			return false;
		} else if(s_passwd.value == "" || s_passwd.value == null) {
			alert("비밀번호를 입력해 주세요")
			s_passwd.focus();
			return false;
		}
	}
	
	if("${msg}") {
		alert("${msg}");
	}
</script>
</html>