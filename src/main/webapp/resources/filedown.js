//파일다운 관련
$(function(){
	$('.input[file="file"]').on('change',function(){
		if(this.files[0]){
			let reader = new FileReader();
			//파일 읽을 시 이벤트 발생
			reader.onload = function(e){
				let value = e.target.result
				if(value.startsWith("data:images/")){
					let imgTag = `<img src="${value}" alt="다운로드 이미지">`
					$('.previe').html(imagTag);
						alert('이미지만 등록 부탁드립니다!');
						$('input[name="imageFileName"]').val('');
						$('.preview').html('');
				}
			}
		reader.readAsDataURL(this.file[0]);
		}
	});//end chang function
});//end function