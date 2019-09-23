<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<style>
	table {
		width:1100px;
		margin:auto;
	}	
	td {
		padding:5px;
	}
</style>
<body>

<form id="register_form" action="register.ja" type="post">
<table>
	<tr>
		<td>제목</td>
		<td><input type="text" id="title" name="title" style="width:650px" value="${board.title }"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<textarea name="smartEditor" id="smartEditor" rows="35" cols="150" placeholder="내용을 입력하세요(4자 이상)">${board.content }</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" style="align:center">
			<input type="button" id="submitBoardBtn" name="submitBoardBtn" value="수정하기">
			<input type="button" onClick="location.href='/'" value="취소">
		</td>
	</tr>
</table>
</form>

</body>
<script>
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "smartEditor", // textarea ID
		sSkinURI : "/smarteditor/SmartEditor2Skin.html",
		htParams : {
			bUseToolbar : true, // 툴바
			bUseVerticalResizer : true, // 크기 조절
			bUseModeChanger : true,
			f0nBeforeUnload : function() {
			}
		},
		f0nAppLoad : function() {
			// 기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할 때 사용
			oEditors.getById["smartEditor"].exeo("PASTE_HTML", [""]);
		},
		fCreator : "createSEditor2"
	}); 
	
	// 네이버 에디터 작성 데이터 전송하기 // 전송 나머지는 JSP/Spring 네이버 스마트~~ 다시 보기
	$("#submitBoardBtn").click(function(){
		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		var content = $('#smartEditor').val();
		var title = $('#title').val();
		var accnt_id = ${login.accnt_id};
		
		
		if(title.trim().length < 1) {
			alert("제목을 입력해주세요");
			$('#title').focus();
		} else {
			$.ajax({
				url : 'update.ja',
				type : 'post',
				data : {
					"title" : title,
					"content" : content,
					"accnt_id" : accnt_id,
					"board_num" : ${board.board_num},
				},
				success : function(data) {
					if(data=="true") {
						alert("성공");
						location.href="/detail.ja?board_num=${board.board_num}"
					}
				}
			});
		}
	});
	
	$('#submitModifyBoardBtn').click(function(){
		oEditors.getByid["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
	});
		
</script>
</html>