var flag;

$( window ).ready(function() {
    connect();
    flag = 0;
});

function connect() {
    var socket = new SockJS('/websocket');
    var textarea_str = "";
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected : " + frame);
        stompClient.subscribe('/topic/message', function (data) {
            console.log("Connected and Subscirbe!");
            var noti = "<p>"+ data + "</p>";
            if (flag == 0){
                textarea_str = textarea_str + noti;
                $('#wrap').html(textarea_str);
                flag++;
            } else {
                textarea_str = "\n" + textarea_str + noti;
                $('#wrap').append(textarea_str);
            }
        });
    });
}