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

// 단건조회 삭제
function confirmDelete(bulletinNo) {
	if (confirm("정말로 삭제하시겠습니까?")) {
		location.href = '/user/bulletinDelete?no=' + bulletinNo;
	}
}


// 가로 드래그 스크롤
window.onload = function() {
    const dragSlider = document.querySelector('.scroll-container');
    if (!dragSlider) {
        console.error('Element with class "scroll-container" not found!');
        return;
    }

    let isDown = false;
    let startX;
    let scrollLeft;

    dragSlider.addEventListener('mousedown', (e) => {
        isDown = true;
        dragSlider.classList.add('active');
        startX = e.pageX - dragSlider.offsetLeft;
        scrollLeft = dragSlider.scrollLeft;
        dragSlider.style.cursor = 'grabbing';
    });

    dragSlider.addEventListener('mouseleave', () => {
        isDown = false;
        dragSlider.classList.remove('active');
        dragSlider.style.cursor = 'grab';
    });

    dragSlider.addEventListener('mouseup', () => {
        isDown = false;
        dragSlider.classList.remove('active');
        dragSlider.style.cursor = 'grab';
    });

    dragSlider.addEventListener('mousemove', (e) => {
        if (!isDown) return;
        e.preventDefault();
        const x = e.pageX - dragSlider.offsetLeft;
        const walk = (x - startX) * 2;  // 드래그 속도 조절
        dragSlider.scrollLeft = scrollLeft - walk;
    });
};
