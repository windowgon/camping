console.log('detail.js');
//게시물
$(function(){
	// 수정 취소 버튼 숨기기
	$('.showMode').hide();
	
	let detailForm = $('#detailForm');//form="id"
	let titleObj = $('input[name="title"]');
	let contentObj = $('textarea[name="content"]');
	let imageFile = "${board.imageFileName}";
	let pTag = $('.preview c').html();
	
	//제목
	let titleVal = titleObj.val();
	//내용
	let contnetVal = contentObj.val();
	//수정전 이미지
	let originImg = $('.originImg').clone();
	
	// 목록
	$('.goList').on('click',function(){
		detailForm.attr({
			"action" : `${contextPath}/board/list`,
			"method" : "get"
		}).empty()
		.submit();
	});//end 목록
	
	// 삭제 처리
	$('.delete').on('click',function(){
		detailForm.attr({
			"action" : `${contextPath}/board/deleteBoard`,
			"method" : "post"
		}).submit();
	});//end 삭제
	
	//수정모드
	$('.toReviseForm').on('click',function(){
		$('input[name="title"],textarea[name="content"]').attr("readonly",false);
		$('.showMode').show();
		$(this).closest('tr').hide();
	});//end 수정모드
	
	
	//수정(취소모드)
	$('.cancle').on('click',function(){
		$('input[name="title"],textarea[name="content"]').attr("readonly",true);
		$('.showMode').hide();
		$(this).closest('tr').prev().show();
		$('.preview').html(originImg);
		$('.input[type="file"]').val('');
		titleObj.val(titleVal);
		contentObj.val(contnetVal);
		if(imageFile == '' || imageFile ==null){
			$('.preview').html(pTag);
		}
	});//end 수정(취소모드)
	
	// 수정 처리
	$('.revise').on('click',function(){
		detailForm.attr({
			"action" : `${contextPath}/board/modBoard`,
			"method" : "post"
		}).submit();
	});// end 수정처리
});//end 게시물 function

//좋아요 기능
$(function(){
	$('.goodCheck').on('click',function(){
		let bno = $('input[name="bno"]').val();
	$.ajax({
		type : "get",
		url :`${contextPath}/board/bGood`,
		data :  {
			bno : bno
		},
		success : function(date){
			alert('게시물 좋아요!')
			location.reload();
		}
	})//ajax end
})//on end
})//function end