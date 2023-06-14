package com.example.websocket.service

import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

import java.util.*
import kotlin.collections.ArrayList

/*
@Service("MainService")
class MainServiceImpl: MainService {*/
@Service("MainService")
class MainServiceImpl : MainService {

    private var sessions: MutableList<WebSocketSession> = Collections.synchronizedList(ArrayList<WebSocketSession>())

    @Override
    override fun addSession(session: WebSocketSession) {
        sessions.add(session)
//        session.sendMessage(TextMessage(session.toString()))
    }

    @Override
    override fun removeSession(session: WebSocketSession) {
        sessions.remove(session)
    }

    @Override
    override fun addEvent(message: String) {
        for (session: WebSocketSession in sessions) {
            session.sendMessage(TextMessage(message))
        }
    }
}