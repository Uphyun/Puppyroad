/*
 * assiInsert.js
 */

$('#insertBtn').on("click", function(){
	
	let title = $('input[name=title]').val();
	let writer = $('input[name=writer]').val();
	let walkPlaceAddress = $("#walkPlaceAddress option:selected").val();
	let content = $('[name=content]').val();
	let matchingKind = '대리';
	
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
	  
	  
});

