$(document).ready(function() {
    // 엔터키로 댓글 등록 처리
    $(document).on('keypress', '.comment-input', function(e) {
        if (e.which === 13) { 
            e.preventDefault(); 
            const bulletinId = $(this).attr('id').split('_')[1]; // bulletinNo 추출
            submitComment(bulletinId);
        }
    });
});

function submitComment(bulletinId) {
	let commentContent = $('#commentInput_' + bulletinId).val(); // 댓글 내용 가져오기

	if (!commentContent) {
		alert("댓글을 입력하세요.");
		return;
	}

	// AJAX 요청으로 댓글 전송
	$.ajax({
		type: 'POST',
		url: '/user/commentInsert',
		data: {
			bulletinNo: bulletinId,
			content: commentContent
		},
		success: function(response) {
			if (response === 1) {
				alert("댓글 등록에 성공했습니다.");
				// 댓글 등록 후 입력창 초기화
				$('#commentInput_' + bulletinId).val('');

			
				/*location.reload()*/
			} else {
				alert("댓글 등록에 실패했습니다.");
			}
		},
		error: function() {
			alert("오류가 발생했습니다. 다시 시도해주세요.");
		}
	});
}

