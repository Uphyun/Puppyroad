/**
 * 
 */
// $(document).ready( function() {
// 	$('#manageTable').DataTable();
// });
//페이지 로딩시 데이터 가져오기
function getMemberList(position, tableId) {
	let dt_memberTable = $('#' + tableId);

	if (dt_memberTable.length) {
		let dt_members = dt_memberTable.DataTable({	//dataTable시작

			//데이터 가져오기
			ajax: {
				url: "/ajax/memberList?position=" + position,
				type: 'GET',
				dataSrc: ''
			},

			// 데이터 설정
			columns: [
				{ data: 'memberCode' },
				{ data: 'nickName' },
				{ data: 'userId' },
				{ data: 'joinDate' },
				{ data: 'activityDate' },
				{ data: 'withdrawDate' },
				{ data: 'accountState' },
			],

			//각 컬럼 데이터 삽입
			columnDefs: [
				{	//1번째 체크박스
					targets: 0,
					searchable: false,
					//responsivePriority: 1,
					render: function (data, type, full, meta) {
						let $memberCode = full['memberCode'];
						return '<input type="checkbox" class="form-check-input" value="' + $memberCode + '"/>';
					}
				},
				{	//2번째 닉네임
					targets: 1,
					//responsivePriority: 2,
					render: function (data, type, full, meta) {
						let $nickName = full['nickName'];
						return $nickName;
					}
				},
				{	//3번째 아이디
					targets: 2,
					//responsivePriority: 3,
					render: function (data, type, full, meta) {
						let $userId = full['userId'];
						return $userId;
					}
				},
				{	//4번째 가입날짜
					targets: 3,
					searchable: false,
					//responsivePriority: 4,
					render: function (data, type, full, meta) {
						let $joinDate = full['joinDate'];
						return $joinDate;
					}
				},
				{	//5번째 활동날짜
					targets: 4,
					searchable: false,
					//responsivePriority: 5,
					render: function (data, type, full, meta) {
						let $activityDate = full['activityDate'];
						return $activityDate;
					}
				},
				{	//6번째 탈퇴날짜
					targets: 5,
					searchable: false,
					//responsivePriority: 6,
					render: function (data, type, full, meta) {
						let $withdrawDate = full['withdrawDate'];
						return $withdrawDate;
					}
				},
				{	//7번째 상세보기
					targets: 6,
					searchable: false,
					//responsivePriority: 7,
					render: function (data, type, full, meta) {
						let $memberCode = full['memberCode'];
						return '<button type="button" class="btn btn-primary waves-effect waves-light viewInfo" data-bs-toggle="modal" data-bs-target="#viewUser" data-memberCode="' + $memberCode + '">상세보기</button>';
					}
				},
				{	//8번째 계정상태
					targets: 7,
					searchable: false,
					//responsivePriority: 7,
					render: function (data, type, full, meta) {
						let $memberCode = full['memberCode'];
						let $accountState = full['accountState'];
						return setStateColumn($accountState, $memberCode);
					}
				},
			],	//데이터삽입 끝

			//order: [0, 'desc'],	//[0]번째 컬럼 내림차순
			//dom:'',	//?
			displayLength: 5,
			lengthMenu: [5, 10, 25, 50, 75, 100],		//페이징 5
			language: {
				sLengthMenu: '_MENU_',
				search: '',
				searchPlaceholder: '아이디 및 닉네임 검색',
				paginate: {
					next: '<i class="ti ti-chevron-right ti-sm"></i>',
					previous: '<i class="ti ti-chevron-left ti-sm"></i>'
				}
			},
			responsive: {
				details: {
					/**
					display: $.fn.dataTable.Responsive.display.modal({
						header: function (row) {
							var data = row.data();
							return 'Details of ' + data['categories'];
						}
					}), */
					type: 'column',
					renderer: function (api, rowIdx, columns) {
						var data = $.map(columns, function (col, i) {
							return col.title !== '' // ? Do not show row in modal popup if title is blank (for check box)
								? '<tr data-dt-row="' +
								col.rowIndex +
								'" data-dt-column="' +
								col.columnIndex +
								'">' +
								'<td> ' +
								col.title +
								':' +
								'</td> ' +
								'<td class="ps-0">' +
								col.data +
								'</td>' +
								'</tr>'
								: '';
						}).join('');

						return data ? $('<table class="table"/><tbody />').append(data) : false;
					}
				}
			}

		});		//dataTable끝
	}
}

//선택된 조건 설정
$("#searchDrop a").on("click", function () {
	$("#searchTypeInput").val($(event.target).text());
	$("#searchDrop button").text($(event.target).text());
})

function setStateColumn(state, code) {
	if (state == 1) {
		return `<div class="position-relative">
					<select onchange="changeState()"
						class="select2 form-select select2-hidden-accessible accountState"
						data-select2-id="accountState" tabindex="-1"
						aria-hidden="true" data-membercode="${code}">
							<option value="1" data-select2-id="2" selected>정상</option>
							<option value="2">휴면</option>
							<option value="3">탈퇴</option>
					</select>
				</div>`;
	} else if (state == 2) {
		return `<div class="position-relative">
					<select onchange="changeState()"
						class="select2 form-select select2-hidden-accessible accountState"
						data-select2-id="accountState" tabindex="-1"
						aria-hidden="true" data-membercode="${code}">
							<option value="1" data-select2-id="2">정상</option>
							<option value="2" selected>휴면</option>
							<option value="3">탈퇴</option>
					</select>
				</div>`;
	} else if (state == 3) {
		return `<div class="position-relative">
					<select onchange="changeState()"
						class="select2 form-select select2-hidden-accessible accountState"
						data-select2-id="accountState" tabindex="-1"
						aria-hidden="true" data-membercode="${code}">
							<option value="1" data-select2-id="2">정상</option>
							<option value="2">휴면</option>
							<option value="3" selected>탈퇴</option>
					</select>
				</div>`;
	}
}

//회원 상제 조회
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