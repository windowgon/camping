<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/member/login.js"></script>
<div class="container">
	<div class="jumbotron">
		<form id="camlist">
			<table class="table">
			    <tr>
			        <th>번호</th>
			        <th>아이디</th>
			        <th>이름</th>
			        <th>이메일</th>
			        <th>전화번호</th>
			        <th>회원삭제</th>
			    </tr>
			    <c:forEach items="${camperList}" var="cam">
			    <tr>
			        <th>${cam.cno}</th>
			        <th>${cam.id}</th>
			        <th>${cam.name}</th>
			        <th>${cam.email}</th>
			        <th>${cam.phone}</th>
			        <th><a href="${contextPath}/camper/removeMember?cno=${cam.cno}">삭제</a></th>
			    </tr>
			    </c:forEach>
			</table>
		</form>	
	</div>
</div>
<%@ include file="../layout/footer.jsp" %>