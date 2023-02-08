<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>캠핑 커뮤니티</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js" integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
 <script>
 	const contextPath = "${contextPath}";
 	let grade= {
 			id : "${grade.id}",
 			grade : "${grade.grade}"
 		};
 </script>
 
<style>
	li {list-style: none;}
	ul {margin:0;padding:0;}
</style>

</head>
<body>
<!-- 게시판 시작 -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">CAMPING</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="${contextPath}/board/home">Home</a></li>
      <li><a href="${contextPath}/board/list">자유게시판</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">이 달의 추천 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">캠핑장</a></li>
          <li><a href="${contextPath}/board/millkit">밀키트</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">고객센터 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">문의사항</a></li>
          <li><a href="#">공지사항</a></li>
        </ul>
      </li>
    </ul>
	     <!-- 로그인,로그아웃 회원가입 -->
	<div align="right">
	  <ul class="navbar-nav">
	  	<c:if test="${empty grade}"> <!-- 세션값이 없을 때 -->
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/camper/joinForm">회원가입</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/camper/loginForm">로그인</a>
	    </li>
	    </c:if>
	    
	    <c:if test="${not empty grade}"> <!-- 세션값이 있을 때 -->
	    <li class="nav-item">
	    	<span class="nav-link">${grade.id}님 로그인 중</span>
	    </li>
	    
	    <c:if test="${grade.grade eq 'ROLE_ADMIN'}">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/camper/camperList">회원리스트</a>
	    </li>
	    </c:if>
	    
	    <c:if test="${grade.id eq board.writer or grade.grade eq 'ROLE_CAMPER'}">
	      <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/board/iWorte">나의정보보기</a>
	    </li>
	    </c:if>
	    
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/camper/logout">로그아웃</a>
	    </li>
	    </c:if>
	  </ul>
  </div>
  	</div>
</nav>
