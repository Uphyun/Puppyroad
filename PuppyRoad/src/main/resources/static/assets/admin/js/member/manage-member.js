/**
 * 
 */
//회원 조회
$('.viewInfo').on("click", function () {
	let memberCode = $(event.target).data("membercode");

	$.ajax({
		type: "get",
		url: "/ajax/manageMember?memberCode=" + memberCode
	})
		.done(result => {
			$("#infoName").text(result.name);
			$("#infoNick").text(result.nickName);
			$("#infoPhone").text(oninputPhone(result.phone));
			$("#infoJoin").text(makeDateFormat(result.joinDate));
			$("#infoActivity").text(makeDateFormat(result.activityDate));
			$("#modalAccountState").val(result.accountState).prop("selected", true);
			$("#modalAccountState").data("membercode", result.memberCode);
			$("#infoAddress").text(result.address);

			let puppyList = result.puppyList;
			$("#puppies").html("");

			puppyList.forEach(puppy => {
				$("#puppies").append(appendPuppy(puppy));
			})
		})
		.fail(err => console.log(err));
})

//날짜 포맷
function makeDateFormat(date) {
	let beforeDate = new Date(date);
	let dateFormat = beforeDate.getFullYear() + "년 "
		+ ((beforeDate.getMonth() + 1) < 10 ? "0" + (beforeDate.getMonth() + 1) : (beforeDate.getMonth() + 1)) + "월 "
		+ (beforeDate.getDate() < 10 ? "0" + beforeDate.getDate() : beforeDate.getDate()) + "일";

	return dateFormat;
}

//전화번호 포맷
function oninputPhone(phone) {
	phone = phone
		.replace(/[^0-9]/g, '')
		.replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");

	return phone;
}

//강아지 목록 만들기
function appendPuppy(puppy) {
	let cardDiv = $("<div>");
	cardDiv.addClass("card mt-2");

	let rowDiv = $("<div>");
	rowDiv.addClass("row");

	//image
	let col2 = $("<div>");
	col2.addClass("col-md-2");
	let imgTag = $("<img>");
	imgTag.addClass("card-img card-img-left");
	imgSrc = getRealData(puppy.picture, "image")
	imgTag.src = ContextPath + "/images/" + imgSrc;
	console.log(imgTag);

	col2.append(imgTag);
	rowDiv.append(col2);


	//cardBody
	let col10 = $("<div>");
	col10.addClass("col-md-10");
	let cardBody = $("<div>");
	cardBody.addClass("card-body");
	//table
	let tableDiv = $("<div>");
	tableDiv.addClass("table-responsive text-nowrap card-text text-center");
	let tableTag = $("<table>");
	tableTag.addClass("table table-borderless");

	let gender = getRealData(puppy.gender, "gender");
	let tr1 = $("<tr>");
	tr1.html(`
                  <th class="text-right">이름 : </th>
                  <td class="text-left">${puppy.dogName}</td>
                  <th class="text-right">견종 : </th>
                  <td class="text-left">${puppy.dogBreed}</td>
                  <th class="text-right">성별 : </th>
                  <td class="text-left">${gender}</td>
                  `);
	tableTag.append(tr1);

	let neutralizationPreAbs = getRealData(puppy.neutralizationPreAbs, "neutralizationPreAbs");
	let diseasePreAbs = getRealData(puppy.diseasePreAbs, "diseasePreAbs");
	let tr2 = $("<tr>");
	tr2.html(`
                  <th class="text-right">나이 : </th>
                  <td class="text-left">${puppy.age} 살</td>
                  <th class="text-right">중성화 유무 : </th>
                  <td class="text-left">${neutralizationPreAbs}</td>
                  <th class="text-right">질병 유무 : </th>
                  <td class="text-left">${diseasePreAbs}</td>
                  `);
	tableTag.append(tr2);

	tableDiv.append(tableTag);
	cardBody.append(tableDiv);
	col10.append(cardBody);
	rowDiv.append(col10);
	cardDiv.append(rowDiv);

	return cardDiv;
}

//유무, 성별판별
function getRealData(data, type) {
	if (type == "gender") {
		if (data == 1) {
			return "수컷";
		} else {
			return "암컷";
		}
	} else if (type == "neutralizationPreAbs" || type == "diseasePreAbs") {
		if (data == 1) {
			return "있음";
		} else {
			return "없음";
		}
	} else if (type == "image") {
		if (data == null) {
			return "Nodata.png";
		} else {
			return data;
		}
	}
}

//계정 상태 변경
function changeState() {
	let memberCode = $(event.target).data("membercode");
	let accountState = $(event.target).val();

	let data = { memberCode, accountState };
	$.ajax({
		method: "put",
		url: "/ajax/changeState",
		contentType: "application/json",
		data: JSON.stringify(data),
	})
		.then(result => {
			if (result.isSuccess) {
				console.log(result.vo.memberCode + " 상태 업데이트 성공!!")
			} else {
				console.log(result.vo.memberCode + " 상태 업데이트 실패...")
			}
		});
}

//아이디 및 닉네임 검색
function searchMember() {
	let condition = $("#conditionInput").val();
	let searchIn = $("#searchInput").val();
	let data = setSearchData(condition, searchIn);

	if (data == null) {
		return alert("검색 조건을 확인 해 주세요");
	}

	$.ajax({
		method: "get",
		url: "/ajax/searchMember",
		data: data,
		success: function(memberList) {
			$("#memberListBody").replaceWith(memberList);
		},
		fail : err => console.log(err)
	});
}

//데이터 설정
function setSearchData(condition, searchIn) {
	let data = { condition };
	if (condition == "아이디") {
		data.userId = searchIn;
	} else if (condition == "닉네임") {
		data.nickName = searchIn;
	} else if (condition == "아이디 및 닉네임") {
		data.userId = searchIn;
		data.nickName = searchIn;
	} else {
		return null;
	}

	return data;
}

//선택된 조건 설정
$("#searchDrop a").on("click", function () {
	$("#conditionInput").val($(event.target).text());
	$("#searchDrop button").text($(event.target).text());
})