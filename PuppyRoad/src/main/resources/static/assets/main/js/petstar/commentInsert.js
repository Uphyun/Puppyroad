// 이미지 미리보기 함수
function previewImage(event) {
	const reader = new FileReader();
	const imageField = $(".profileImagePreview");

	// 파일이 있는지 확인하고 읽기
	if (event.target.files && event.target.files[0]) {
		reader.onload = function(e) {
			imageField.attr("src", e.target.result);
		};
		reader.readAsDataURL(event.target.files[0]);
	}
}


// 단건조회 삭제
function confirmDelete(bulletinNo) {
	if (confirm("정말로 삭제하시겠습니까?")) {
		location.href = '/user/bulletinDelete?no=' + bulletinNo;
	}
}

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
				// 댓글 등록 후 입력창 초기화
				$('#commentInput_' + bulletinId).val('');

				location.reload()
			} else {
				alert("댓글 등록에 실패했습니다.");
			}
		},
		error: function() {
			alert("오류가 발생했습니다. 다시 시도해주세요.");
		}
	});
}


// 댓글 더보기
window.showAllComments = function(bulletinNo) {
    const hiddenComments = document.querySelectorAll('#commentList_' + bulletinNo + ' .hidden-comment');
    hiddenComments.forEach(function(comment) {
        comment.style.display = 'flex';  
    });
    document.getElementById('moreCommentsBtn_' + bulletinNo).style.display = 'none';  // 더보기 버튼 숨기기
};

