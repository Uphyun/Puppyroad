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
		let url = context + '/matchInfo?bulletinNo=' + bno;
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