<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>TEST</title>
</head>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<%--<script type="text/javascript" src="js/app.js"></script>--%>
<body>
<script type="text/javascript">
    var ws;
    $(document).ready(function() {
        connect();
    });

    function connect(){
        // Websocket 오브젝트 생성
        var socket = new WebSocket("ws://127.0.0.1:8080/ws");
        // 서버 시작 동작(onopen 함수 호출)
        socket.onopen = function (e){
            console.log("[open] Create Connection");
            console.log("Data Server send!!!");
        };
        // 서버로부터 메시지를 전달받을 때 동작
        socket.onmessage = function(e){
            console.log("[message] Server to Data : " + e.data);
            $("#wrap").append("<p>" + e.data + "</p>");
        };
        $("#wrap").scrollTop = $("#wrap").scrollHeight;
        // 에러 발생 시 동작
        socket.onerror = function (e){
            console.log("[error] " + e.data);
        };
        // 연결 종료 시 동작
        socket.onclose = function (e){
            if(e.wasClean){
                console.log("[close] Connection Finished. [code = " + e.code + ", reason = " + e.reason + "]");
            } else{
                console.log("[close] Connection Dead.");
            }
        };
    }
</script>
<div id="wrap" style="overflow-scrolling: auto">
</div>
</body>
</html>
