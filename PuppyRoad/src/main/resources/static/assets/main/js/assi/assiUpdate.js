/*
 * assiUpdate.js
 */

const context = $('meta[name="contextPath"]').attr('value');

$("#updateBtn").on("click", function() {
	
	let title = $('input[name=title]').val();
	let walkPlaceAddress = $('input[name=walkPlaceAddress]').val();
	let content = $('[name=content]').val();
	let bulletinNo = $('input[name=bulletinNo]').val();
	
	let data = {title, content, walkPlaceAddress, bulletinNo}
	console.log(data);
	
	$.ajax({
		url : '/user/assiUpdate',
		type: "post",
		contentType: "application/json",
		data: data,
		success: function(datas) {
			console.log(datas);
		}
	})
	  .fail(err => console.log(err));





});





