/**
 * room.js
 */


$(document).ready(function(){


    console.log(roomName + ", " + roomId + ", " + username);

    var sockJs = new SockJS("/stomp/chat");
    //1. SockJS를 내부에 들고있는 stomp를 내어줌
    var stomp = Stomp.over(sockJs);

    //2. connection이 맺어지면 실행
    stomp.connect({}, function (){
       console.log("STOMP Connection")

       //4. subscribe(path, callback)으로 메세지를 받을 수 있음
       stomp.subscribe("/sub/chat/room/" + roomId, function (chat) {
           var content = JSON.parse(chat.body);

           var writer = content.writer;
           var str = '';
           var message = content.message;

           if(writer === username){
               str = "<div class='col-6'>";
               str += "<div class='alert alert-secondary'>";
               str += "<b>" + writer + " : " + message + "</b>";
               str += "</div></div>";
               $("#msgArea").append(str);
           }
           else{
               str = "<div class='col-6'>";
               str += "<div class='alert alert-warning'>";
               str += "<b>" + writer + " : " + message + "</b>";
               str += "</div></div>";
               $("#msgArea").append(str);
           }

       });

       //3. send(path, header, message)로 메세지를 보낼 수 있음
       stomp.send('/pub/chat/enter', {}, JSON.stringify({roomId: roomId, writer: username}))
    });

    $("#button-send").on("click", function(e){
        var msg = document.getElementById("msg");

        console.log(username + ":" + msg.value);
        stomp.send('/pub/chat/message', {}, JSON.stringify({roomId: roomId, message: msg.value, writer: username}));
        msg.value = '';
    });
    
    $("#msg").on("keyup", function(e){
		if(e.keyCode==13){
        var msg = document.getElementById("msg");

        console.log(username + ":" + msg.value);
        stomp.send('/pub/chat/message', {}, JSON.stringify({roomId: roomId, message: msg.value, writer: username}));
        msg.value = '';
		}
    });
    
});