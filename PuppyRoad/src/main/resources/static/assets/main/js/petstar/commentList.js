//단건조회 삭제
function confirmDelete(no) {
	if (confirm("정말로 삭제하시겠습니까?")) {
		location.href = '/user/commentDelete?no=' + no;
	}
}