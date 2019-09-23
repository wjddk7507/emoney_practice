<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>join</title>
</head>
<style>
	table{
		width:1000px;
		margin:auto;
		border-top: 2px solid;
		border-collapse: collapse;
	}
	tr{
		border-bottom : 1px solid;
		width:620px;
		height:50px;
	}
	th{
		padding:10px;
		width:180px;
		background-color:#f5f5f5;
	}
	.board_input{
		width:200px;
		height:25px;
		margin-left:20px;
	}
	.btn_wrap{
		margin:auto;
	}
	span{
		font-size:11px;
		color:grey;
	}
	img{
		vertical-align:middle;
	}
</style>
<body>

<div class="join_wrap" id="join_wrap">
	<p align="center">회원가입</p>
	<div class="board_wrap">
		<form id="join_form" method="post" onsubmit="return check()">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" id="board_id" name="id" class="board_input" maxlength="12">
						<a href="javascript:idCheck()"><img src="//img.x1.co.kr/x1/images/btn/btn_duplication.gif" alt="중복확인"></a>
						<span>4자이상 12자이하 영문,숫자(띄어쓰기, 특수문자 불가)</span>
						<span id="idOff" style="display:inline;color:red;">중복확인을 해주세요</span>
						<span id="idOn" style="display:none;color:green;">사용가능합니다</span>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" id="board_pw"  name="s_passwd" class="board_input" maxlength="12">
						<span id="pwOff">영어 대/소문자, 숫자, 특수문자 중 2가지 이상 조합 6자~12자(띄어쓰기 불가) </span>
						<span id="pwOn" style="display:none;color:green;">사용가능합니다</span>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" id="board_pwconfirm" class="board_input" maxlength="12" required="required"></td>
				</tr>
				<tr>
					<th>성명</th>
					<td><input type="text" name="name" class="board_input" maxlength="6" pattern="[가-힣]{2,6}" required="required"></td>
				</tr>
				<tr>
					<th>필명(닉네임)</th>
					<td>
						<input type="text" id="board_nickname" name="nickname" class="board_input" maxlength="12" required="required">
						<a href="javascript:nicknameCheck()"><img src="//img.x1.co.kr/x1/images/btn/btn_duplication.gif" alt="중복확인"></a>
						<span>영문 4자~12자, 한글 2자~6자(띄어쓰기, 특수문자 불가)</span>
						<span id="nicknameOff" style="display:inline;color:red;">중복확인을 해주세요</span>
						<span id="nicknameOn" style="display:none;color:green;">사용가능합니다</span>
					</td>
				</tr>
				<tr>
					<th>휴대폰번호</th>
					<td>
						<select class="phone" name="phone1" style="margin-left:20px;">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
							<option value="070">070</option>
						</select>-
						<input type="text" name="phone2" maxlength="4" style="width:50px;" required="required" pattern="[0-9]{1,4}">-
						<input type="text" name="phone3" maxlength="4" style="width:50px;" required="required" pattern="[0-9]{1,4}">
					</td>
				</tr>
				<tr style="border-bottom:1px white">
					<td align="center" colspan="2">
						<input type="button" value="취소" onClick="location.href='/'" class="btn btn-default" >						
						<input type="submit" value="회원가입" class="btn btn-default" />
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>

<script>

	var idConfirm = false; // id 중복여부 상태 확인
	var nicknameConfirm = false; // nickname 중복 여부 상태 확인
	
	// 정규식
	var p1 = /[0-9]/;
	var p2 = /[a-zA-Z]/;
	var p3 = /[~!@#$%^&*()?]/;
	var p4 = /[ㄱ-ㅎㅏ-ㅣ가-힣]/;
	var p5 = /[ㄱ-ㅎㅏ-ㅣ]/;
	var blank_pattern = /[\s]/g;
	
	function check() { // form에서 submit 했을 때 호출 / false를 리턴하면 서버로 전송X
		
		if(idConfirm == false) {
			alert("아이디 중복여부를 확인해 주십시오");
			return false;
		}
		if(nicknameConfirm == false) {
			alert("닉네임 중복여부를 확인해 주십시오");
			return false;
		}	
	
		var pw = document.getElementById("board_pw");
		var pwconfirm = document.getElementById("board_pwconfirm");
		
		if(pw.value != pwconfirm.value){
			alert("동일한 비밀번호를 입력하셔야 합니다");
			return false;
		}
			
		// 숫+영, 숫+특, 영+특
		if(pw.value.length < 6 || blank_pattern.test(pw.value) || ( ( !p1.test(pw.value) || !p2.test(pw.value) ) && (!p2.test(pw.value) || !p3.test(pw.value)) && (!p1.test(pw.value) || !p3.test(pw.value)) )){
			alert("비밀번호 입력이 잘못되었습니다.")	
			pw.focus;
			return false;
		}
		
	}
	
	// 비밀번호 입력받으면서 유효성 검사
	document.getElementById("board_pw").onkeyup = function() {
		var pw = document.getElementById("board_pw");
		var pwconfirm = document.getElementById("board_pwconfirm");
		if(pw.value.length < 6 || blank_pattern.test(pw.value) || ( ( !p1.test(pw.value) || !p2.test(pw.value) ) && (!p2.test(pw.value) || !p3.test(pw.value)) && (!p1.test(pw.value) || !p3.test(pw.value)) )   ){
			document.getElementById("pwOff").style.display = 'inline';
			document.getElementById("pwOn").style.display = 'none';
		} else {
			document.getElementById("pwOn").style.display = 'inline';
			document.getElementById("pwOff").style.display = 'none';
		}
	};
	
	// id, 닉네임 중복체크 후에도 아이디 변경 후 가입을 방지하기 위해
	document.getElementById("board_id").onkeyup = function() {
		idConfirm=false;
		document.getElementById("idOn").style.display = 'none';	
		document.getElementById("idOff").style.display = 'inline';
	};
	// id, 닉네임 중복체크 후에도 아이디 변경 후 가입을 방지하기 위해
	document.getElementById("board_nickname").onkeyup = function() {
		nicknameConfirm=false;
		document.getElementById("nicknameOn").style.display = 'none';	
		document.getElementById("nicknameOff").style.display = 'inline';
	};
	
	function idCheck() {
		var id = document.getElementById("board_id").value;
		var idOff = document.getElementById("idOff");
		var idOn = document.getElementById("idOn");
		
		if( id.length < 4 || blank_pattern.test(id) || p3.test(id) || p4.test(id)){
			alert("아이디 입력이 잘못되었습니다");
			idOn.style.display = 'none';	
			idOff.style.display = 'inline';
			return false;
		}
		
		$.ajax({
	        url:'idCheck',
	        data:{'id':id},
	        success:function(data){
	                if(data=="true"){
	                	// 중복 X
	                	idOff.style.display = 'none';
	                	idOn.style.display = 'inline';
	                	idConfirm = true;
	                	alert("사용 가능 합니다");
	                } else {
	                	alert("이미 사용중인 아이디입니다");
	                	idOff.style.display = 'inline';
	                	idOn.style.display = 'none';
	                }
	        }
	});
	}
	
	function nicknameCheck() {
		var nickname = document.getElementById("board_nickname").value;
		
		if( byteCheck(nickname) > 12 || p1.test(nickname) || p3.test(nickname) || p5.test(nickname) || blank_pattern.test(nickname)){
			alert("닉네임 입력이 잘못되었습니다");	
        	nicknameOn.style.display = 'none';
        	nicknameOff.style.display = 'inline';
			return false;
		}
		
		$.ajax({
	        url:'nicknameCheck',
	        data:{'nickname':nickname},
	        success:function(data) {
	                if(data=="true"){ 
	                	nicknameOff.style.display = 'none';
	                	nicknameOn.style.display = 'inline';
	                	nicknameConfirm = true;
	                	alert("사용 가능 합니다");
	                } else {
	                	alert("이미 사용중인 닉네임입니다");
	                	nicknameOff.style.display = 'inline';
	                	nicknameOn.style.display = 'none';
	                }
	        }
	});
	}
	
	function byteCheck(text) {
		var codeByte = 0;
		for(var idx=0; idx < text.length ; idx++) {
			var oneChar = escape(text.charAt(idx));
			if(oneChar.length == 1) {
				codeByte ++;
			} else if(oneChar.indexOf("%u") != -1) {
				codeByte +=2;
			}
		}
		return codeByte;
	}
	
	
</script>

</body>
</html>