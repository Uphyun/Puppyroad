/*
 * assiInfo.js
 */
const context = $('meta[name="contextPath"]').attr('value');

function application(event) {
	let bulletinNo = $('input[name=bulletinNo]').val();
	let writer = $('input[name=writer]').val();
	let title = $('input[name=title]').val();

	$.ajax({
		url: '/user/matchChatCheck',
		method: 'get',
		data: {bulletinNo},
		success: function(datas) {
			if (datas > 0) {
				alert('이미 신청하였습니다.')
	
			} else {
				let url = context + '/user/matchChat?writer=' + writer + '&title=' + title + '&chattingType=1' + '&bulletinNo=' + bulletinNo;
				location.href = url;
	
			}
		}
	})

}

