/*
 * matchList.js
 */
let puppyCode = '';
let bulletinNo = '';

const btn = document.getElementById("popBtn"); // 모달을 띄우는 버튼 요소 가져오기
const modal = document.getElementById("modalWrap"); // 모달 창 요소 가져오기
const closeBtn = document.getElementById("closeBtn"); // 모달을 닫는 버튼(X) 요소 가져오기

btn.onclick = function() {
	modal.style.display = "block"; // 버튼을 클릭하면 모달을 보이게 함
};

closeBtn.onclick = function() {
	modal.style.display = "none"; // 모달을 닫는 버튼(X)을 클릭하면 모달을 숨김
};

window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none"; // 모달 외부를 클릭하면 모달을 숨김
	}
};


function getCheckboxValue(event) {
	if (event.target.checked) {
		var div = $(event.target).closest(".abc");
		puppyCode = div.find('#puppyCode').val();

		var picture = div.find('[name=puppyImage]').attr("src");
		var dogBreed = div.find('#dogBreed').html();
		var personality = div.find('#personality').html();
		var dogSize = div.find('#dogSize').html();
		var neutralizationPreAbs = div.find('#neutralizationPreAbs').html();
		var diseasePreAbs = div.find('#diseasePreAbs').html();


		$(".first").clone().attr('id', puppyCode).insertBefore("#second").removeClass("first").addClass("road").show();
		var div2 = $("#" + puppyCode);

		div2.find('[name=puppyImage2]').attr("src", picture);
		div2.find('[name=dogBreed]').val(dogBreed);
		div2.find('[name=personality]').val(personality);
		div2.find('[name=dogSize]').val(dogSize);
		div2.find('[name=neutralizationPreAbs]').val(neutralizationPreAbs);
		div2.find('[name=diseasePreAbs]').val(diseasePreAbs);

	} else {
		var div = $(event.target).closest(".abc");
		puppyCode = div.find('#puppyCode').val();
		$('#' + puppyCode).remove();

	}
}

$('#insertBtn').on("click", function(e) {

	let title = $('input[name=title]').val();
	let writer = $('input[name=writer]').val();
	let walkPlaceAddress = $("#walkPlaceAddress option:selected").val();
	let content = $('[name=content]').val();
	let matchingKind = '자율';

	let puppie = [];
	$('.road').each(function(idx, item) {
		let puppyCode = $(item).attr('id');

		puppie.push({ puppyCode });
	})


	if (title == '') { //title input이 공백일 경우
		e.preventDefault() // 폼 전송을 막음
		alert('제목을 입력해주세요') // '제목을 입력해주세요' 라는 경고창을 띄움
	}
	else if (puppie == '') { // puppie가 공백일 경우
		e.preventDefault() // 폼 전송을 막음
		alert('반려견정보를 입력해주세요') // '반려견정보를 입력해주세요' 라는 경고창을 띄움
	}
	else if (content == '') { // content input이 공백일 경우
		e.preventDefault() // 폼 전송을 막음
		alert('소개를 입력해주세요') // '소개를 입력해주세요' 라는 경고창을 띄움
	}
	else {

		let bNo = { bulletinNo };
		let data = { title, writer, walkPlaceAddress, content, matchingKind, puppie };

		$.ajax({
			url: '/user/matchChatCheck',
			method: 'get',
			contentType: 'application/json',
			data: JSON.stringify(bNo),
			success: function(datas) {
				
				if (datas.result > 0) {
					alert('매칭을 이미 신청하였습니다.')
					
					
				} else {

					$.ajax({
						url: '/user/matchInsert',
						method: 'post',
						contentType: 'application/json',
						data: JSON.stringify(data),
						success: function(datas) {
							if (datas.result = 1) {
								alert("성공적으로 등록되었습니다.");
								location.href = "/user/matchList"
							} else {
								alert("등록 오류")
							}
							
						}
						
					})
						.fail(err => console.log(err))

				}
			}
		})


	}
});

