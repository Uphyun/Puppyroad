// 이미지 미리보기 함수
function previewImage(event) {
	const reader = new FileReader();
	const imageField = document.getElementById("profileImagePreview");

	reader.onload = function() {
		if (reader.readyState === 2) {
			imageField.src = reader.result;
		}
	};
	reader.readAsDataURL(event.target.files[0]);
}

function confirmSubmit() {
	return confirm('정말로 수정하시겠습니까?');
}