$(".odd").on('click', (e)=>{
		$("#listDis").hide();
		let param = $(e.currentTarget).data("puppycode");
		
		$.ajax({
			url : "/user/getInfoPuppy?puppyCode=" + param,
			method : "get",
			
		}).done((result)=>{
			
		if (result.diseasePreAbs == 0) {
            result.diseasePreAbs = "무";
        } else {
            result.diseasePreAbs = "유";
        }

			
			
		let puppyInfo = `
			<div class="card mb-6">
			<div class="card-body pt-12" style="width:380px">
				<div class="user-avatar-section">
					<div class="d-flex align-items-center flex-column">
						<img class="img-fluid rounded mb-4" src="/images/${result.picture}" height="240" width="240" alt="No image">
						<div class="user-info text-center">
						</div>
					</div>
				</div>
				<div class="info-container">
				<div style="margin:auto">
	            <ul class="list-unstyled mb-6">
				<div class="avatar">
					<div class="avatar-initial bg-label-primary rounded">
						<i class="ti ti-checkbox ti-lg"></i>
					</div>
				</div>
				<div>
	                <li class="mb-2"><span class="h6"> 이름 : </span><span>${result.dogName}</span></li>
	                <li class="mb-2"><span class="h6"> 나이 : </span><span>${result.age}</span></li>
	                <li class="mb-2"><span class="h6"> 견종 : </span><span>${result.dogBreed}</span></li>
	                <li class="mb-2"><span class="h6"> 크기 : </span><span>${result.dogSize}</span></li>
	                <li class="mb-2"><span class="h6"> 무게 : </span><span>${result.weight}kg</span></li>
	                <li class="mb-2"><span class="h6"> 성격 : </span><span>${result.personality}</span></li>
	                <li class="mb-2"><span class="h6"> 질병 : </span><span id="baddis">${result.diseasePreAbs}</span></li>
	                <li class="mb-2"><span class="h6"> 노트 : </span><span>${result.note}</span></li>
	                <li class="mb-2" style="display:none"><span class="h6"> 번호 </span><input id="num" value="${result.puppyCode}" /></li>
	                <div class="d-flex justify-content-center">
						<a href="javascript:;"
							class="btn btn-primary me-4 waves-effect waves-light"
							data-bs-target="#editUser" data-bs-toggle="modal" onclick="updatePuppy()">수정</a> <a
							href="javascript:;"
							class="btn btn-label-danger suspend-user waves-effect" onclick="deletePuppy()">삭제</a>
					</div>
	             <div>
	            </ul>
	        </div>
			</div>
			</div>
		</div>
		
        `;
        

		 $(".info-container").html(puppyInfo);
		})

	})
	
	//수정버튼클릭시
	function updatePuppy(){
		let para = $("#num").val();
		$.ajax({
			url : "/user/getInfoPuppy?puppyCode=" + para,
			method : "get",
			
		}).done((result)=>{
				console.log(result.diseasePreAbs);
				$("#profileImagePreview").attr("src", "/images/"+result.picture);
				$("#hiddenPuppyCode").val(result.puppyCode);
				$("#modalEditPuppyName").val(result.dogName);
				$("#modalEditUserLastName").val(result.age); // 나이
		        $("#modalEditUserName").val(result.dogBreed); // 견종
		        $("#modalEditUserEmail").val(result.weight); // 무게
		        $("#modalEditUserStatus").val(result.dogSize); // 크기
		        $("#note").val(result.note); // 노트
		        $("#disease").val(result.diseasePreAbs).prop("selected", true); // 질병
		        $("#personality").val(restult.personality);
		        $("#neutralization").val(result.neutralizationPreAbs).prop("selected", true); // 질병
			
		})
	}

//이미지 미리보기
function previewImage(event) {
    const input = event.target;
    const reader = new FileReader();
    reader.onload = function(){
        const imagePreview = document.getElementById('profileImagePreview');
        imagePreview.src = reader.result;
        imagePreview.style.display = 'block'; // 이미지가 보이도록 설정
    }
    reader.readAsDataURL(input.files[0]);
}

//삭제버튼
function deletePuppy() {
    let puppyCode = $("#num").val();
    console.log(puppyCode);
    $.ajax({
        url: "/user/deletePuppy",
        method: "post",
        data: { puppyCode: puppyCode },
        success: (result) => {
            if (result === "success") {
				alert("삭제성공!!")
                window.location.href = "/user/listPuppy";  
            }
        },
        error: (err) => {
            console.error("Error deleting puppy:", err);
        }
    });
}

//수정완료시 alert
function handleSubmit(event) {
		alert("수정이 완료되었습니다!");
		document.getElementById("editUserForm").submit();  
}

//페이지 랜더링시 결제내역리스트
$(document).ready(function(){
	$.ajax({
		url: '/user/payList',
		method: 'GET'
		
	}).done((result)=>{
		console.log(result);
		for(let res of result){
			console.log(res.recipient);
			$('.payList').append(
			`<li class="timeline-item timeline-item-transparent"><span
									class="timeline-point timeline-point-primary"></span>
									<div class="timeline-event">
										<div class="timeline-header mb-3">
											<h6 class="mb-0">도그워커 : ${res.name}</h6>
											<small class="text-muted">결제일 : ${res.purchasedAt}</small>
										</div>
										<div class="d-flex align-items-center mb-2">
											<div class="badge bg-lighter rounded d-flex align-items-center">
												 <span class="h6 mb-0 text-body">결제방식 : ${res.method}</span>
											</div>
										</div>
										<div class="d-flex align-items-center mb-2">
											<div class="badge bg-lighter rounded d-flex align-items-center">
												 <span class="h6 mb-0 text-body">가격 : ${res.price}원</span>
											</div>
										</div>
									</div>
								</li>`)
		}
	})
})
	

