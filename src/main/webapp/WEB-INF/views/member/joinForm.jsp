<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/member/joinForm.js"></script>
<div class="container">
	<div class="jumbotron">
		<h1>회원가입</h1>
		<form id="frm" action="${contextPath}/camper/join" method="post">
		<div class="form-group">
			<label for="user_id">아이디</label><button type="button" id="checkId" class="btn btn-link idcheck">아이디 중복확인</button>
				<input type="text" class="form-control" id="user_id" name="id" placeholder="ID" style="width:300px;height:30px;font-size:18px;" required>
		</div>
		<!-- 비밀번호 -->
		<div class="form-group">
			<label for="user_pwd">비밀번호</label>
				<input type="password" class="form-control" id="user_pwd" name="pwd" placeholder="PASSWORD" style="width:300px;height:30px;font-size:18px;" required>
		</div>
		<!-- 비밀번호 -->
		<div class="form-group">
			<label for="user_pwd">비밀번호 확인</label>
				<input type="password" class="form-control" id="user_pwd2" name="pwd" placeholder="PASSWORD" style="width:300px;height:30px;font-size:18px;" required>
		</div>
		<!-- 이름 -->
		<div class="form-group">
			<label for="user_name">이름</label>
				<input type="text" class="form-control" id="user_name" name="name" placeholder="Name" style="width:300px;height:30px;font-size:18px;" required>
		</div>
		<!-- 생년월일 -->
		<div class="form-group">
			<label for="user_email">이메일</label>
				<input type="text" class="form-control" id="user_email" name="email" placeholder="EMAIL" style="width:300px;height:30px;font-size:18px;" required>
		</div>
		<div class="form-group">
			<label for="user_phone">전화번호</label>
				<input type="text" class="form-control" id="user_phone" name="phone" placeholder="PHONE" onkeyup="test(this);" style="width:300px;height:30px;font-size:18px;" required>
		</div>
			<button type="button" class="btn btn-lg btn-success btn-block DosignUp" style="width:300px;height:40px;font-size:18px;" >회원가입</button>
		</form>
	</div>	
</div>
<%@ include file="../layout/footer.jsp" %>
