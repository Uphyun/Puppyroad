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

function confirmSubmit() {
	return confirm('정말로 수정하시겠습니까?');
}