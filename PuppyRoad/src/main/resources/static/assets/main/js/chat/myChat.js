/**
 * myChat.js
 */
$(document).ready(function() {
	
	let bulletinNo = '';
	let title = '';
	let roomId = '';
	let writer = '';
	
	//calender 30분주기
	$('.flatpickr-minute').attr("step", 30);
	
	
	var sockJs = new SockJS("/stomp/chat");
	//1. SockJS를 내부에 들고있는 stomp를 내어줌
	var stomp = Stomp.over(sockJs);

	//2. connect
	stomp.connect({}, function() {
		console.log("STOMP Connection")
		
		$('.room').each(function(idx, item) {
			//4. subscribe(path, callback)으로 메세지를 받을 수 있음
			stomp.subscribe("/sub/chat/room/" + item.id, function(chat) {
				var content = JSON.parse(chat.body);

				var writer = content.writer;
				let app = '';
				var message = content.message;

				if (writer === username) {
		app = `<li class='chat-message chat-message-right'>
		 <div class='d-flex overflow-hidden'>
		 <div class='chat-message-wrapper flex-grow-1'>
		 <div class='chat-message-text'>
		 <p class='mb-0'>${message}</p>
		 </div>
		 <div class='text-end text-muted mt-1'>
		 <small></small>
		 </div></div>
		 <div class='user-avatar flex-shrink-0 ms-4'>
		 <div class='avatar avatar-sm'>
		 <img src='/assets/admin/img/avatars/1.png' alt='Avatar' class='rounded-circle' />
		 </div></div></div></li>`

		$('.list-unstyled.chat-history').append(app);
				}
				else {
		app = `<li class='chat-message'>
		 <div class='d-flex overflow-hidden'>
		 <div class='chat-message-wrapper flex-grow-1'>
		 <div class='chat-message-text'>
		 <p class='mb-0'>${message}</p>
		 </div>
		 <div class='text-end text-muted mt-1'>
		 <small></small>
		 </div></div>
		 <div class='user-avatar flex-shrink-0 ms-4'>
		 <div class='avatar avatar-sm'>
		 <img src='/assets/admin/img/avatars/1.png' alt='Avatar' class='rounded-circle' />
		 </div></div></div></li>`

		$('.list-unstyled.chat-history').append(app);
				}
				let bot2 = document.querySelector('.chat-history-body').scrollHeight
				document.querySelector('.chat-history-body').scroll(0, bot2);

			});
			
			//3. send(path, header, message)로 메세지를 보낼 수 있음
			stomp.send('/pub/chat/enter', {}, JSON.stringify({ roomId: roomId, writer: username }))
		});



		$('li.chat-contact-list-item').on('click', function() {
			
			title = $(this).find('h6').attr('id');
			bulletinNo = $(this).find('h6').attr('name');
			roomId = $(this).attr('id');
			writer = $(this).attr('name');

			$('.list-unstyled.chat-history').empty();
			$('#chatProfile').empty();
			
			let chatPro = '';
			
			chatPro = `<i class='ti ti-menu-2 ti-lg cursor-pointer d-lg-none d-block me-4'
                              data-bs-toggle='sidebar'
                              data-overlay
                              data-target='#app-chat-contacts'></i>
                            <div class='flex-shrink-0 avatar'>
                              <img
                                src='/assets/admin/img/avatars/4.png'
                                alt='Avatar'
                                class='rounded-circle'
                                data-bs-toggle='sidebar'
                                data-overlay
                                data-target='#app-chat-sidebar-right'/>
                            </div>
                            <div class='chat-contact-info flex-grow-1 ms-4'>
                              <h6 class='m-0 fw-normal'>${roomId}</h6>
                              <small class='user-status text-body'>${roomId}</small>
                            </div>`;
			
			$('#chatProfile').append(chatPro);

			//ajax 호출 
			$.ajax({
				url: '/chat/message',
				method: 'get',
				data: { roomId }
			})
				.done(datas => {
					for (let str in datas) {
						let app = '';

						if (datas[str].writer === username) {

					app = `<li class='chat-message chat-message-right'>
					 <div class='d-flex overflow-hidden'>
					 <div class='chat-message-wrapper flex-grow-1'>
					 <div class='chat-message-text'>
					 <p class='mb-0'>${datas[str].message}</p>
					 </div>
					 <div class='text-end text-muted mt-1'>
					 <small></small>
					 </div></div>
					 <div class='user-avatar flex-shrink-0 ms-4'>
					 <div class='avatar avatar-sm'>
					 <img src='/assets/admin/img/avatars/1.png' alt='Avatar' class='rounded-circle' />
					 </div></div></div></li>`
							$('.list-unstyled.chat-history').append(app);
						}
						else {
							app = `<li class='chat-message'>
					 <div class='d-flex overflow-hidden'>
					 <div class='chat-message-wrapper flex-grow-1'>
					 <div class='chat-message-text'>
					 <p class='mb-0'>${datas[str].message}</p>
					 </div>
					 <div class='text-end text-muted mt-1'>
					 <small></small>
					 </div></div>
					 <div class='user-avatar flex-shrink-0 ms-4'>
					 <div class='avatar avatar-sm'>
					 <img src='/assets/admin/img/avatars/1.png' alt='Avatar' class='rounded-circle' />
					 </div></div></div></li>`
							$('.list-unstyled.chat-history').append(app);
						}
					}
					document.querySelector('.chat-history-body').scroll(0, 0);
					setTimeout(function(){
						let bot2 = document.querySelector('.chat-history-body').scrollHeight
						document.querySelector('.chat-history-body').scroll(0, bot2);
					}, 30);
				})
				.fail(err => console.log(err))
				
			$.ajax({
				url: '/chat/address',
				method: 'get',
				data: { writer }
			})
			  .done( datas => {
				$('#walkPlaceAddress').val('');
				let address = datas[0].walkPlaceAddress
				let content = datas[0].content
				let startDate = datas[0].startTime
				let endDate = datas[0].endTime
				
				$('#walkPlaceAddress').val(address);
				$('#content').val(content);
				$('#eventStartDate').val(startDate);
				$('#eventEndDate').val(endDate);
				
				
				
			  });
			
		});

	});



	$("#button-send").on("click", function(e) {
		var msg = document.getElementById("msg");

		console.log(username + ":" + msg.value);
		stomp.send('/pub/chat/message', {}, JSON.stringify({ roomId: roomId, message: msg.value, writer: username }));
		msg.value = '';
		

	});
	
	$("#addSchedul").on("click", function(e) {
		
	let startTime =	$("input[name='eventStartDate']").val()
	let endTime = $("input[name='eventEndDate']").val()
	let walkPlaceAddress = $('input[name=walkPlaceAddress]').val();
	let content = $('input[name=content]').val();
	let matchingKind = '대리';
	
	let puppy = [];
	$('.select2-selection__choice').each(function(idx, item){
		let puppyCode = $(item).attr("title");
		
		puppy.push({puppyCode});
	})
		
	let data = {bulletinNo, title, writer, walkPlaceAddress, startTime, endTime, content, matchingKind}
	console.log(data);
		
		$.ajax({
			url: '/user/matchUpdate',
			method: 'post',
			contentType : 'application/json',
			data: 'JSON.stringify(data)',
			success: function(datas) {
				if(datas.result = 1) {
					alert("성공적으로 신청되었습니다.");
				} else {
					alert("신청 오류")
				}
			}
		})
		  .fail(err => console.log(err))
	}) 


});

