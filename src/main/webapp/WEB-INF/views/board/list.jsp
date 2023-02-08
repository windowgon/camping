<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/list.js"></script>
<div class="container my-3">
	<form id="listForm">
		<table class="table">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>캠핑족</th>
				<th>작성날짜</th>
				<th>좋아요</th>
				<c:forEach items="${list}" var="c"> 
				<tr>
					<td>
						${c.bno}
					</td>
					<td>
						<a href="${contextPath}/board/detail?bno=${c.bno}">${c.title}<b>${c.replyCount != 0 ? '('+=c.replyCount+=')':''}</b></a>
						
 					</td>
 					<td>${c.writer}</td>
 					<td>${c.writeDate}</td>
 					<td>${c.good}</td>
				</tr>
				</c:forEach>
		</table>
	</form>
</div>
<a href="${contextPath}/board/writeForm" class="btn btn-primary float-right">글쓰로가자</a>

<!-- 페이징 처리 -->

<%@ include file="../layout/footer.jsp" %>
