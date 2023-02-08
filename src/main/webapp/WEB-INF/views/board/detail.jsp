<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<script src="${contextPath}/resources/js/board/detail.js"></script>
<script src="${contextPath}/resources/js/reply/replyAll.js"></script>


<div class="container">
	<div class="jumbotron">
		<h1>글 자세히 보기!</h1>
	</div>
	<form id="detailForm" enctype="multipart/form-data">
	<table class="table">
		<tr>
			<th>글 번호</th>
			<td>
				${board.bno}
				<input type="hidden" name="bno" value="${board.bno}">
			</td>
			<th>좋아요</th>
			<td>
				${board.good}
				<input type="hidden" name="good" value="${board.good}">
			</td>
		</tr>
		
		<tr>
			<th>캠핑족</th>
			<td>
				${board.writer}
			</td>
			<th>작성일</th>
			<td>${board.writeDate}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">
				<input type="text" name="title" class="form-control" value="${board.title}" readonly="readonly">
			</td>
		<tr>
			<th>내용</th>
			<td colspan="3">
				<textarea rows="10" name="content" class="form-control" readonly="readonly" >${board.content}</textarea>
			</td>
		</tr>
		<tr>
			<th>이미지첨부</th>
			<td colspan="3">
				<input type="file" name="imageFileName" class="form-control showMode">
				<div class="my-3">
				<c:if test="${not empty board.imageFileName}">
					<input type="hidden" name="originFileName" value="${board.imageFileName}">
					<div class="preview">
						<img class="originImg" src="${contextPath}/fileDownload?no=${board.bno}&imageFileName=${board.imageFileName}&path=board">
					</div>
				</c:if>
				<c:if test="${empty board.imageFileName}">
					<div class="preview">
						<p>등록된 이미지가 없습니다.</p>
					</div>
				</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="text-center">
			<c:if test="${grade.id eq board.writer or grade.grade eq 'ROLE_ADMIN'}">
				<button type="button" class="btn btn-info toReviseForm">수정하기</button>
				<button type="button" class="btn btn-danger delete">삭제</button>
			</c:if>
				<button type="button" class="btn btn-success goList">목록</button>
				<c:if test="${grade.grade eq 'ROLE_ADMIN' or grade.grade eq 'ROLE_CAMPER'}">
				<button type="button" class="btn btn-warning goodCheck">♥</button>
				</c:if>
				<c:if test="${grade.id eq null}">
				<button type="button" class="btn btn-warning goodCheck" disabled="disabled">♥는 로그인이 필요합니다.</button>
				</c:if>
			</td>
		</tr>
		</tr>
		<tr class="showMode">
			<td colspan="4" class="text-center">
			<c:if test="${grade.id eq board.writer or grade.grade eq 'ROLE_ADMIN'}">
				<button type="button" class="btn btn-outline-secondary revise">수정</button>
				<button type="button" class="btn btn-outline-dark cancle">취소</button>
			</c:if>			
			</td>
		</tr>
	</table>
	</form>
<!-- 댓글구현 -->
	<div class="replyForm">
		<table class="table">
			<tr>
				<th colspan="2" >
					<ul class="d-flex justify-content-between">
						<li>댓글을 작성해주세요</li>
						<li class="form-inline">작성자 : <input type="text" class="reply_writer form-control ml-2" value="${grade.id}" readonly="readonly"></li>
					</ul>
				</th>
			</tr>

			<tr>
				<td class="col-1 text-center" >내용</td>
				<td>
					<textarea rows="5" class="form-control reply_content" name="modreply"></textarea>
				</td>
			</tr>
			 <c:if test="${grade.id eq null}">
			<tr class="text-right">
				<td colspan="2"><button class="btn btn-primary reply_write" disabled="disabled">로그인후 이용해주세요</button></td>
			</tr>
			 </c:if>
			  <c:if test="${grade.grade eq 'ROLE_ADMIN' or grade.grade eq 'ROLE_CAMPER'}">
			<tr class="text-right">
				<td colspan="2"><button class="btn btn-primary reply_write">댓글작성</button></td>
			</tr>
			 </c:if>
		</table>
	</div>
	<div class="replyList">
		<div class="card">
		  <div class="card-header bg-info text-white">댓글목록 ${board.replyCount}</div>
		  <div class="card-body">
			<ul class="list-group list-group-flush"></ul>
		  </div>
		</div>
	</div>

	<div class="modal fade" id="feedback">
	  <div class="modal-dialog">
	    <div class="modal-content">
	    
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">댓글 등록</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      
	      <div class="modal-body">
	        Modal body..
	      </div>
	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
</div> 
<%@ include file="../layout/footer.jsp" %>