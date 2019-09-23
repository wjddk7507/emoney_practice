<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- 부트스트랩 스타일시트 파일 링크 설정
contextPath는 절대 경로를 만들기 위해서 추가 -->
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

</head>

<title>junga</title>
</head>
<body class="skin-blue sidebar-mini">

		<aside class="main-sidebar" style="margin-bottom:20px;">
			<section class="sidebar">
				<ul class="nav nav-tabs">
					<li role="presentation" style="margin-right:30px;"><a href="javascript:void(0);"></a></li>
					<li role="presentation"><a href="/" style="color:#00d2b5">Home</a></li>
					<li role="presentation"><a href="list.ja">게시물 보기</a></li>
					<li role="presentation"><a href="register.ja">게시물 쓰기</a></li>
					<li role="presentation"><a href="javascript:typeCheck();">회원정보 보기</a></li>
					<li role="presentation" style="margin-right:1000px;"><a href="javascript:void(0);"></a></li>
					<c:if test="${login == null }">
						<li role="presentation"><a href="join.ja">회원가입</a></li>
						<li role="presentation"><a href="login.ja">로그인</a></li>
					</c:if>
					<c:if test="${login.nickname != null}">
						<li role="presentation" class="disabled"><a href="javascript:void(0)"><span style="color:#00d2b5">${login.nickname}</span>님 로그인하셨습니다</a></li>
						<li role="presentation"><a href="logout.ja">로그아웃</a></li>
					</c:if>
				</ul>	
					
			</section>
		</aside>
</body>
<script>
	
	
	var type="${login.user_type}";
	function typeCheck(){
			if(!type) {
				alert("관리자만 접근이 가능합니다.");
			} else{
				location.href = "account.ja";
			}			
	}
</script>
</html>