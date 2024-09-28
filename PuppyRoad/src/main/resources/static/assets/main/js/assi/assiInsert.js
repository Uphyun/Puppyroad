/*
 * assiInsert.js
 */

$('#insertBtn').on("click", function(e){
	
	let title = $('input[name=title]').val();
	let writer = $('input[name=writer]').val();
	let walkPlaceAddress = $("#walkPlaceAddress option:selected").val();
	let content = $('[name=content]').val();
	let matchingKind = '대리';
	
	if (title == '') { //title input이 공백일 경우
        e.preventDefault() // 폼 전송을 막음
        alert('제목을 입력해주세요') // '제목을 입력해주세요' 라는 경고창을 띄움
    }
    else if (content == '') { // content input이 공백일 경우
        e.preventDefault() // 폼 전송을 막음
        alert('소개를 입력해주세요') // '소개를 입력해주세요' 라는 경고창을 띄움
    }
	else{
		
	let data = {title, writer, walkPlaceAddress, content, matchingKind};
	
	console.log(data);
	
	$.ajax({
		url : '/user/assiInsert',
		method : 'post',
		data : data,
		success: function(datas) {
			if(datas.result = 1) {
				alert("성공적으로 등록되었습니다.");
				location.href = "/user/assiList"
			} else {
				alert("등록 오류")
			}
		}
	})
	  .fail(err => console.log(err))
	  
	}
	  
});

