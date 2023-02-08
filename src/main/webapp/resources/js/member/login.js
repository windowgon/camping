//로그인 관련
$(function(){
	$('.login').on('click',function(){//from=id
		let userId = $('input[name="id"]').val();
		let pwd = $('input[name="pwd"]').val();
		$.ajax({
			type : 'post',
			url : `${contextPath}/camper/login`,
			data : {
				id : userId,
				pwd : pwd
			},
			//성공
			success : function(result){
				alert(result);
				location.replace('http://localhost:8090/camping/board/list');
			},
			//에러	
			error : function(result){
				alert(result);
			}
		});//ajax end
	});// on end
});//function end


