<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 수정 - 운영자 권한</title>
</head>
<style>
th{
color:#00d2b5;
text-align:center;
}
</style>
<body>
<form action="user_typeUpdate.ja" method="post" onsubmit="return check()">
		<table id="account_info" style="width:1500px;margin:auto;text-align:center">
				<tr>
					<th width="5%">ACCNT_ID</th>
					<th width="20%">NICKNAME</th>
					<th width="10%">NAME</th>
					<th width="5%">USER_TYPE</th>
					<th width="10%">PHONE</th>
					<th width="15%">ID</th>
					<th width="25%">S_PASSWD</th>
					<th width="10%">last_login</th>
				</tr>
					<tr>
						<td><input type="hidden" name="accnt_id" value="${account.accnt_id}">${account.accnt_id}</td>
						<td>
							<input name="nickname" id="nickname" size="12" type="text" maxlength="12" required="required" value="${account.nickname}">
							<input type="button" id="nikBtn" onClick="nicknameCheck()" value="중복확인" style="display:none">
						<span id="nicknameOn" style="display:inline;color:green;">사용 가능</span>
						</td>
						<td><input name="name" type="text" size="12" maxlength="6" pattern="[가-힣]{2,6}" required="required" value="${account.name}"></td>
						<td>
							<select name="user_type">
								<option value="">일반</option>	
								<option value="A" <c:out value="${account.user_type=='A'?'selected=selected':' '}"/>>A</option>
								<option value="E" <c:out value="${account.user_type=='E'?'selected=selected':' '}"/>>E</option>					
							</select>
						</td>
						<td><input type="text" name="phone" value="${account.phone}"></td>
						<td>${account.id}</td>
						<td><input type="text" id="pw" name="s_passwd" size="35" value="${account.s_passwd}"></td>
						<td>${account.last_login}</td>
					</tr>
			</table>

		<div style="text-align:center;margin-top:10px">
			<input type="button" class="btn btn-default" value="취소" onClick="location.href='/account.ja'">
			<input type="submit" class="btn btn-default" value="수정하기">
		</div>
</form>
</body>
<script>
	var nicknameConfirm = false; // nickname 중복 여부 상태 확인
	var nikBtn = document.getElementById("nikBtn");
	
	// 정규식
	var p1 = /[0-9]/;
	var p2 = /[a-zA-Z]/;
	var p3 = /[~!@#$%^&*()?]/;
	var nickp1 = /[가-힣]{2,6}/;
	var nickp2 = /[a-zA-Z]{4,12}/;
	var blank_pattern = /[\s]/g;
	
	$("#nickname").on("change keyup paste", function() {
		nicknameOn.style.display = 'none';
		nikBtn.style.display = 'inline';
		
		if(document.getElementById("nickname").value == "${account.nickname}") {
			nicknameOn.style.display = 'inline';
			nikBtn.style.display = 'none';
			nicknameConfirm = true;
		}
	});
	
	function check() { // form에서 submit 했을 때 호출 / false를 리턴하면 서버로 전송X
		var pw = document.getElementById("pw");
	
		if(nickname = document.getElementById("nickname").value == "${account.nickname}") {
			nicknameConfirm = true;
		}
		
		if(nicknameConfirm == false) {
			alert("닉네임 중복여부를 확인해 주십시오");
			return false;
		}	
		
		// 숫+영, 숫+특, 영+특
		if(pw.value.length < 6 || blank_pattern.test(pw.value) || ( ( !p1.test(pw.value) || !p2.test(pw.value) ) && (!p2.test(pw.value) || !p3.test(pw.value)) && (!p1.test(pw.value) || !p3.test(pw.value)) )){
			alert("비밀번호 입력이 잘못되었습니다.")	
			pw.focus;
			return false;
		}
	}
	
	function nicknameCheck() {		
		var nickname = document.getElementById("nickname").value;
		if( ( !nickp1.test(nickname) && !nickp2.test(nickname) ) || p1.test(nickname) || p3.test(nickname) || blank_pattern.test(pw.value) ){
			alert("닉네임 입력이 잘못되었습니다");	
			return false;
		}
		
			$.ajax({
		        url:'nicknameCheck',
		        data:{'nickname':nickname},
		        success:function(data) {
		                if(data=="true"){ 
		                	nicknameOn.style.display = 'inline';
		        			nikBtn.style.display = 'none';
		                	nicknameConfirm = true;
		                	alert("사용 가능 합니다");
		                } else {
		                	alert("이미 사용중인 닉네임입니다");
		                	nicknameOn.style.display = 'none';
		        			nikBtn.style.display = 'inline';
		                }
		        }
		});
	}
	
	
</script>
</html>