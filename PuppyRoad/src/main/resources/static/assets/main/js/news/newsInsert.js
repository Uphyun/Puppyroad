var fileNo = 0;
var filesArr = [];



/* 첨부파일 추가 */
function addFile(obj) {
	var maxFileCnt = 5;   // 첨부파일 최대 개수
	var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
	var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부 가능한 개수
	var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

	// 첨부파일 개수 확인
	if (curFileCnt > remainFileCnt) {
		alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
		return;
	}

	for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) {
		const file = obj.files[i];

		// 중복된 파일이 있는지 확인
		if (filesArr.some(f => f.name === file.name && f.size === file.size)) {
			alert("이미 선택된 파일입니다: " + file.name);
			continue;
		}

		// 첨부파일 검증
		if (validation(file)) {
			var reader = new FileReader();
			reader.onload = function(e) {
				filesArr.push(file);

				// 목록 추가
				let htmlData = '';
				htmlData += '<div id="file' + fileNo + '" class="filebox">';
				htmlData += '   <img src="' + e.target.result + '" alt="' + file.name + '" style="max-width: 100px; max-height: 100px; display: block;"/>';
				htmlData += '   <p class="name">' + file.name + '</p>';
				htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
				htmlData += '</div>';
				$('#image_list').append(htmlData);
				fileNo++;
			};
			reader.readAsDataURL(file);
		}
	}

	// 초기화
	obj.value = "";
}

/* 첨부파일 검증 */
function validation(file) {
	const fileTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif'];
	if (file.name.length > 100) {
		alert("파일명이 100자 이상인 파일은 제외되었습니다.");
		return false;
	} else if (file.size > (10 * 1024 * 1024)) {  // 10MB 제한
		alert("최대 파일 용량인 10MB를 초과한 파일은 제외되었습니다.");
		return false;
	} else if (!fileTypes.includes(file.type)) {
		alert("첨부가 불가능한 파일은 제외되었습니다.");
		return false;
	}
	return true;
}

/* 첨부파일 삭제 */
function deleteFile(num) {
	$("#file" + num).remove();
	filesArr.splice(num, 1);
}

/* 폼 전송 */
function submitForm() {
	var form = document.querySelector("form");
	var formData = new FormData(form);

	// 제목, 내용, bulletinType 수동 추가
	formData.append("title", $('input[name="title"]').val());
	formData.append("content", $('textarea[name="content"]').val());
	formData.append("bulletinType", $('input[name="bulletinType"]:checked').val());

	let title = $('input[name="title"]');
	if (title.val() === '') {
		alert('제목이 입력되지 않았습니다.');
		title.focus();
		event.preventDefault();
		return;
	}

	let content = $('textarea[name="content"]');
	if (content.val() === '') {
		alert('내용이 입력되지 않았습니다.');
		content.focus();
		event.preventDefault();
		return;
	}

	// 삭제되지 않은 파일만 폼데이터에 담기
	for (var i = 0; i < filesArr.length; i++) {
		formData.append("files", filesArr[i]);
	}

	$.ajax({
		method: 'POST',
		url: '/user/newsInsert',  // 실제 처리할 URL로 변경
		data: formData,
		processData: false,
		contentType: false,
		success: function(data) {
			if (data > 0) {
				alert("성공적으로 제출되었습니다.");
				location.href = "/user/newsList"
			} else {
				alert("등록 오류.");
			}
		},
		error: function(xhr, desc, err) {
			alert('제출 중 오류가 발생했습니다.');
		}
	});
}


// 단건조회 삭제
function confirmDelete(bulletinNo) {
	if (confirm("정말로 삭제하시겠습니까?")) {
		location.href = '/user/newsDelete?no=' + bulletinNo;
	}
}