package com.example.websocket.handler

import com.example.websocket.service.MainService
import com.example.websocket.service.MainServiceImpl
import jakarta.annotation.Resource
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*

open class WebSocketHandler : TextWebSocketHandler() {

    @Resource(name = "MainService")
    private lateinit var service: MainService

    @Override
    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        service.addSession(session)
        println("연결 : $session")
    }

    @Override
    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        service.removeSession(session)
        println("연결 종료 : $session")
    }
}

/*private var webSocketSessions: MutableList<WebSocketSession> = Collections.synchronizedList(ArrayList<WebSocketSession>())

//    웹소켓 연결
    @Override
    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        webSocketSessions.add(session)
        println("Session : $webSocketSessions")
    }

//    소켓 연결 종료
    @Override
    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        webSocketSessions.remove(session)
        println("연결 종료 : " + session.id)
    }

//    양방향 데이터 통신
    @Override
    @Throws(Exception::class)
    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        super.handleTextMessage(session, message)
//    for문이 없으면 다른 session 값이
        for (websocketSession: WebSocketSession in webSocketSessions) {
            websocketSession.sendMessage(message)
        }
        session.sendMessage(TextMessage("Received : $message"))
    }*/