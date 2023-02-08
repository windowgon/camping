<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>


<div class="container">
	<div class="jumbotron">
<table border="2">

		    <tr>
		        <th>번호</th>
		        <th>제목</th>
		        <th>내용</th>
		        <th>작성자</th>
		        <th>댓글갯수</th>
		        <th>좋아요갯수</th>
		        <th>작성날짜</th>
		    	<th>내 글로가기</th>
		    </tr>
		    <c:forEach items="${wroteList}" var="w">
		    <tr>
		        <th>${w.bno}</th>
		        <th>${w.title}</th>
		        <th>${w.content}</th>
		        <th>${w.writer}</th>
		        <th>${w.replyCount}</th>
		        <th>${w.good}</th>
		        <th>${w.writeDate}</th>
		        <th><a href="${contextPath}/board/detail?bno=${w.bno}">확인</a></th>
		        
		    </tr>
		    </c:forEach>
		</table>
	</div>
</div>
<%@ include file="../layout/footer.jsp" %>