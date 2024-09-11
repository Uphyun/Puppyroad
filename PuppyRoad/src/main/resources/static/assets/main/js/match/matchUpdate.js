/*
 * matchList.js
 */

const context = $('meta[name="contextPath"]').attr('value');
$("#updateBtn").on("click", updateAjax);

function updateAjax(event) {
  // 보낼 데이터 가져오기
  let dataObj = getFormData();
  // AJAX
  $.ajax("matchUpdate", {
    type: "post",
    contentType: "application/json",
    data: JSON.stringify(dataObj),
  })
    .done((data) => {
      if (data.result) {
        alert("정상적으로 수정되었습니다");
		let bno = $('#bulletinNo').val();
		let url = context + '/user/matchInfo?bulletinNo=' + bno;
		location.href = url;
      } else {
        alert("수정이 실패하였습니다");
      }
    })
    .fail((err) => {
      console.log(err);
    });
}

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
	var dogBreed = div.find('#dogBreed').html();
	var personality = div.find('#personality').html();
	var dogSize = div.find('#dogSize').html();
	var neutralizationPreAbs = div.find('#neutralizationPreAbs').html();
	var diseasePreAbs = div.find('#diseasePreAbs').html();
	
	$("#first").clone().attr('id', puppyCode).insertBefore("#second");
	
	var div2 = $("#" + puppyCode);
	div2.find('label').remove();
	div2.find('#popBtn').remove();
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

$('#insertBtn').on("click", function(){
  let checkedList = [];
  
  $("input[name=dogBox]:checked").each(function(){
        if($(this).is(":checked")==true){
          let pupCode = $(this).closest(".abc").find('#puppyCode').val()
          checkedList.push(pupCode);
        } 
  });
          console.log(checkedList);
          $("#puppies").val(checkedList);
    $("form[name='insertForm']").submit();      
  //return checkedList;
	
});