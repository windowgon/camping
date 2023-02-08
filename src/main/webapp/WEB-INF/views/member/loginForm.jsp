<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/member/login.js"></script>
<div class="container">
	<div class="jumbotron">
		<h1>로그인</h1>
	</div>
	<form action="${contextPath}/camper/login" method="post">
		<div class="form-camper">
			아이디 : <input type="text" class="form-camper" name="id">
		</div>
		<div class="form-camper">
			비밀번호 : <input type="password" class="form-camper" name="pwd">
		</div>
		<button class="btn btn-primary login">로그인</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>