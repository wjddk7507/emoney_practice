<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eMoney 신입 실습 - 이정아</title>
</head>
<body>

<div class="col-12 text-center mb-4 mt-5">
	<h1><a href="/">eMoney</a></h1>
</div>

<%@ include file = "header.jsp"%>

<div style="width:1200px;margin:auto;">
<div class="inline-block">
	<div style="width:70%;float:right">	
		<img src="/resources/img/landing_1.png" alt="image">
	</div>
	<div style="width:30%;float:left;margin-top:220px">
			<h1 style="font-weight:800;font-size:48px">LEE JUNG A<br/>이정아<br/>1994.04.08</h1>
			<p id='currentDate' style="font-size:20px"></p>
	</div>
</div>
</div>

<script>
	var d = new Date();
	var currentDate = d.getFullYear() + "년 " + (d.getMonth()+1) + "월 " + d.getDate() + "일";
	var currentTime = d.getHours() + "시 " + d.getMinutes() + "분 " + d.getSeconds() + "초";
	document.getElementById('currentDate').innerHTML = currentDate + " " + currentTime;	
</script>
</body>
</html>