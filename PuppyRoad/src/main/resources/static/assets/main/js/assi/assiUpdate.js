/*
 * assiUpdate.js
 */

const context = $('meta[name="contextPath"]').attr('value');

$("#updateBtn").on("click", function() {

	let title = $('input[name=title]').val();
	let walkPlaceAddress = $("#walkPlaceAddress option:selected").val();
	let content = $('[name=content]').val();
	let bulletinNo = $('input[name=bulletinNo]').val();

	let data = { title, content, walkPlaceAddress, bulletinNo }

	$.ajax({
		url: '/user/assiUpdate',
		type: "post",
		data: data,
		success: function(datas) {
			if (datas.result = 1) {
				alert("성공적으로 수정되었습니다.");
				location.href = '/user/assiList'
			} else {
				alert("수정 오류")
			}
		}
	})
		.fail(err => console.log(err))





});





