<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file = "../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 보기</title>
</head>
<style>
.pagination {
    display: flex;
    padding-left: 0px;
    list-style: none;
    border-radius: 0.25rem;
    justify-content: center;
}
th {
	text-align:center;
}
</style>
<body>
	
	<table id="board_info" class="table table-hover" style="width:800px;margin:auto;text-align:center;">
		<tr class="active">
			<th width="100px" onclick="event.cancelBubble=true" >글번호</th>
			<th width="300px" onclick="event.cancelBubble=true" >제목</th>
			<th width="100px" onclick="event.cancelBubble=true" >작성자</th>
			<th width="100px" onclick="event.cancelBubble=true" >작성일</th>
		</tr>
		<c:forEach var="vo" items="${map.list }">
			<tr style="cursor:pointer">
				<td>${vo.board_num }</td>
				<td style="text-align:left"><a href="detail.ja?board_num=${vo.board_num}">${vo.title }</a></td>
				<td>${vo.accnt_id }</td>
				<td>${vo.regdate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 페이지 번호 출력 영역 -->
		<div>
			<ul class="pagination mx-auto text-center">
				<c:if test="${map.pageMaker.totalCount > 0}">
					<!-- 이전 링크 -->
					<c:if test="${map.pageMaker.prev}">
						<li><a href="list.ja?page=${map.pageMaker.startPage-1}&perPageNum=${map.pageMaker.criteria.perPageNum}">이전</a></li>
					</c:if>
					<!-- 페이지 번호 -->
					<c:forEach var="idx" begin="${map.pageMaker.startPage}" end="${map.pageMaker.endPage}">
						<li <c:out value="${map.pageMaker.criteria.page==idx?'class=active':''}"/>><a href="list.ja?page=${idx}&perPageNum=${map.pageMaker.criteria.perPageNum}">${idx}</a></li>
					</c:forEach>
					<!-- 다음 링크 -->
					<c:if test="${map.pageMaker.next}">
						<li><a href="list.ja?page=${map.pageMaker.endPage+1}&perPageNum=${map.pageMaker.criteria.perPageNum}">다음</a></li>
					</c:if>
					
				</c:if>
			</ul>
		</div>
	
</body>
<script>
$("#board_info tr").click(function(){
	var td = $(this).children();
	var board_num = td.eq(0).text();
	location.href="detail.ja?board_num="+board_num;
});
</script>
</html>