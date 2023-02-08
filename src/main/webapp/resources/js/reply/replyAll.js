console.log('replyAll.js');

$(function(){
	let bno = $('input[name="bno"]').val();
	
	
	// 댓글 리스트 실행
	replyAll.list(bno);
	
	// 댓글 쓰기 버튼 이벤트
	$('.reply_write').on('click',function(){
		let writer = $('.reply_writer').val();
		let reply = $('.reply_content').val();
		
		let replyVO = {
			bno : bno, 
			reply : reply, 
			writer : writer
		}		
		replyAll.write(replyVO);
	});
	
	// 댓글완료 수정 버튼 이벤트
	$('.replyList').on('click','.reply_modBtn',function(){
		let cno = $(this).closest('div').data('cno');
		let reply = $('.reply_content').val();
		alert(cno + "번 수정요청")
		let replyVO = {
			cno : cno, 
			reply : reply, 
		}	
		replyAll.modreply(cno);
	});
	
// 댓글 삭제 버튼 이벤트
	$('.replyList').on('click','.reply_delBtn',function(){
		let cno = $(this).closest('div').data('cno');
		let bno = $('input[name="bno"]').val();
		replyAll.delreply(cno,bno);
		alert('댓글을 삭제합니다')
	});//end 삭제
});//function 종료


let replyAll = {
	//댓글목록 불러오기
	list : function(bno){
		$.ajax({
			type : 'get', 
			url : `${contextPath}/reply/list`,
			data : {bno : bno},
			success : function(replyList){
				console.log(bno+'번 댓글 리스트 불러오기');
				replyListRender(replyList);
			}, 
			error: function(){
				alert('댓글목록 조회 실패')				
			}
		});	// ajax end	
	},
	//댓글쓰기
	write : function(replyVO){
		$.ajax({
			type : 'post', 
			url : `${contextPath}/reply/write`, 
			data : replyVO, 
			success : function(result){
				$('.reply_content').val('');
				$('#feedback').find('.modal-body').html(result);
				$('#feedback').modal('show');
				replyAll.list(replyVO.bno);

			}, 
			error : function(){
				alert('댓글 등록 에러');
			}
		}); // ajax end 
	},
	//댓글삭제
	delreply : function(cno, bno){
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/delreply`,
			data : {
				cno : cno,
				bno : bno
				},
			success : function(result){
				alert(result)
				location.reload();
			},
			error : function(){
				location.reload();
			}
		})//ajax end
	}//del function end
};





	
	
// 댓글 화면 렌더링
function replyListRender(replyList){
	let output = '';
	for(let r of replyList){
		output += 
			`<li class="list-group-item d-flex justify-content-between">
				<div>
					<p>${r.reply}</p>
					<span class="badge badge-success">${r.writer}</span>
					<span class="badge badge-info">${r.replyDate}</span>
				</div>`
				
		if(r.writer==grade.id && grade.grade=='ROLE_CAMPER' ){ // 로그인한 사용자
			output+= `
			<div class="align-self-center" data-cno="${r.cno}">
				<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>
			</div>`;
		}
		if(grade.grade=='ROLE_ADMIN'  && grade.id!=r.writer){
			output+= `
			<div class="align-self-center" data-cno="${r.cno}">
				<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>
			</div>`;			
			
		} else if(grade.grade=='ROLE_ADMIN' && grade.id==r.writer){
			output+= `
			<div class="align-self-center" data-cno="${r.cno}">
				<button class="btn btn-sm btn-danger reply_delBtn">삭제</button>
			</div>`;			
		}
	}
	output += `</li>`;
	$('.replyList ul').html(output);	
}