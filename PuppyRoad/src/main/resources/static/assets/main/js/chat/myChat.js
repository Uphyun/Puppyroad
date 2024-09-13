/**
 * myChat.js
 */
$(document).ready(function() {
	let roomId = '';
	
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
		 <i class='ti ti-checks ti-16px text-success me-1'></i>
		 <small>${message}</small>
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
		 <i class='ti ti-checks ti-16px text-success me-1'></i>
		 <small>${message}</small>
		 </div></div>
		 <div class='user-avatar flex-shrink-0 ms-4'>
		 <div class='avatar avatar-sm'>
		 <img src='/assets/admin/img/avatars/1.png' alt='Avatar' class='rounded-circle' />
		 </div></div></div></li>`

		$('.list-unstyled.chat-history').append(app);
				}

			});
			
			//3. send(path, header, message)로 메세지를 보낼 수 있음
			stomp.send('/pub/chat/enter', {}, JSON.stringify({ roomId: roomId, writer: username }))
		});



		$('li.chat-contact-list-item').on('click', function() {
			$('.chat-history-body').scrollTop($('.chat-history-body')[0].scrollHeight);
			
			roomId = $(this).attr('id');

			$('.list-unstyled.chat-history').empty();

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
					 <i class='ti ti-checks ti-16px text-success me-1'></i>
					 <small>${datas[str].message}</small>
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
					 <i class='ti ti-checks ti-16px text-success me-1'></i>
					 <small>${datas[str].message}</small>
					 </div></div>
					 <div class='user-avatar flex-shrink-0 ms-4'>
					 <div class='avatar avatar-sm'>
					 <img src='/assets/admin/img/avatars/1.png' alt='Avatar' class='rounded-circle' />
					 </div></div></div></li>`
							$('.list-unstyled.chat-history').append(app);
						}
					}

				})
				.fail(err => console.log(err))
				
		});

	});



	$("#button-send").on("click", function(e) {
		var msg = document.getElementById("msg");
		
		this.event.bind(this.element, 'scroll', function (e) { return this$1.onScroll(e); });

		console.log(username + ":" + msg.value);
		stomp.send('/pub/chat/message', {}, JSON.stringify({ roomId: roomId, message: msg.value, writer: username }));
		msg.value = '';
		
		$('.chat-history-body').scrollTop($('.chat-history-body')[0].scrollHeight);

	});


});

