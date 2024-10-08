/*
 * matchList.js
 */

const context = $('meta[name="contextPath"]').attr('value');
$("#updateBtn").on("click", function() {

	let title = $('input[name=title]').val();
	let walkPlaceAddress = $("#walkPlaceAddress option:selected").val();
	let matchingState = '1';
	let content = $('[name=content]').val();
	let bulletinNo = $('input[name=bulletinNo]').val();
	
	let puppy = [];
	$('.road').each(function(idx, item){
		let puppyCode = $(item).attr('id');

		puppy.push({bulletinNo, puppyCode});
	})
	console.log(puppy);

	let data = { title, content, matchingState, walkPlaceAddress, bulletinNo }

	$.ajax({
		url: '/user/matchUpdate',
		type: "post",
		data: data,
		dataType: "json",
		success: function(datas) {
			if (datas.result = 1) {
				
			} else {
				alert("수정 오류")
			}
		}
	})
		.fail(err => console.log(err))
		
	
	$.ajax({
		url: '/user/matchDogUpdate',
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(puppy),
		success: function(datas) {
			if (datas.result = 1) {
				alert("수정 되었습니다")
				location.href = "/user/matchList"
			} else {
				alert("수정 오류")
			}
		}
		
	})
	  .fail(err => console.log(err))
	  

});




function getFormData() {
  let formAry = $('form[name="updateForm"]').serializeArray();
  let formObj = {};
  $.each(formAry, function (idx, tag) {
    formObj[tag.name] = tag.value;
  });

  return formObj;
}


let puppyCode = '';

const btn = document.getElementById("popBtn"); // 모달을 띄우는 버튼 요소 가져오기
const modal = document.getElementById("modalWrap"); // 모달 창 요소 가져오기
const closeBtn = document.getElementById("closeBtn"); // 모달을 닫는 버튼(X) 요소 가져오기

btn.onclick = function () {
  modal.style.display = "block"; // 버튼을 클릭하면 모달을 보이게 함
};

closeBtn.onclick = function () {
  modal.style.display = "none"; // 모달을 닫는 버튼(X)을 클릭하면 모달을 숨김
};

window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none"; // 모달 외부를 클릭하면 모달을 숨김
  }
};


function getCheckboxValue(event)  {
  if(event.target.checked)  {
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
	
  }else {
	var div = $(event.target).closest(".abc");
	puppyCode = div.find('#puppyCode').val();
	$('#' + puppyCode).remove();
	
  } 
}