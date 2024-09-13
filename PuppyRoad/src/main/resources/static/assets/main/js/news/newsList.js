
$(document).ready(function() {
	// 제목 클릭 시 해당 게시물로 이동
	$('.title').on('click', function() {
		let trTag = $(this).closest('tr');
		let bno = trTag.children().first().text().trim();  // 번호 가져오기
		let url = '/user/newsInfo?bulletinNo=' + bno;
		location.href = url;
	});
});


// 단건조회 삭제
function confirmDelete(bulletinNo) {
	if (confirm("정말로 삭제하시겠습니까?")) {
		location.href = '/user/newsDelete?no=' + bulletinNo;
	}
}