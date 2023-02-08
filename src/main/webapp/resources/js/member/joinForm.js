//회원가입 유효성
function DosignUp(id_check_result, check_userid){
	var userid = $('#user_id').val();
	var userpwd = $('#user_pwd').val();
	var userpwd2 = $('#user_pwd2').val();
	var username = $('#user_name').val();
	var useremail = $('#user_email').val();
	var userphone = $('#user_phone').val();
			
	if(userid.length == 0 ){
		alert("아이디를 입력해 주세요");
		$("#user_id").focus();
		return ;
	}
	if(userpwd.length == 0 ){
		alert("비밀번호를 입력해 주세요");
		$("#user_pwd").focus();
		return ;
	}
	if(userpwd2.length == 0 ){
		alert("비밀번호 확인을 입력해 주세요");
		$("#user_pwd2").focus();
		return ;
	}
	if(userpwd2 != userpwd ){
		alert("비밀번호가 일치하지 않습니다.다시 입력해주세요");
		$("#user_pwd2").focus();
		return ;
	}
	if(useremail.length == 0 ){
		alert("이메일을 입력해 주세요");
		$("#user_email").focus();
		return ;
	}
	if(userphone.length == 0 ){
		alert("전화번호 입력해 주세요");
		$("#user_phone").focus();
		return ;
	}
	if (check_userid != userid){
		id_check_result = false;
	}
	if(id_check_result == false){
      alert("아이디 체크 중복 확인");
      return ;		
   }
   if(confirm("회원가입을 하시겠습니까?")){
        alert("회원가입을 축하합니다");
        $('#frm').submit();
    }
}

//아이디 중복체크
$(function(){
	let id_check_result = false;
	let check_userid;
	
	$('.DosignUp').on('click',function(){
		DosignUp(id_check_result, check_userid);
	}) // 회원가입 완료
	
	$('.idcheck').on('click',function(){
		check_userid = $('input[name="id"]').val();
		$.ajax({
			type : 'post',
			url : `${contextPath}/camper/idcheck`, 
			data : {
				id : check_userid
			},
			//성공
			success : function(result){
				if(result=="1"){
					alert('사용할수 없음')
					id_check_result = false;
				}else{
					alert('사용가능')
					id_check_result = true;
				} 
			},
			error : function(){
				alert("서버요청실패");
			}
		});//ajax end
	});//on end
});//function end
//전화번호(제약 사항 하이폰 생성)
   function test(e) {
        var number = e.value.replace(/[^0-9]/g, "");
        var phone = "";
 
        if (number.length < 4) {
            return number;
        } else if (number.length < 7) {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3);
        } else if (number.length < 10) {
            phone += number.substr(0, 2);
            phone += "-";
            phone += number.substr(2, 3);
            phone += "-";
            phone += number.substr(5);
        } else if (number.length < 11) {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3, 3);
            phone += "-";
            phone += number.substr(6);
        } else {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3, 4);
            phone += "-";
            phone += number.substr(7, 4);
        }
        e.value = phone;
    }
