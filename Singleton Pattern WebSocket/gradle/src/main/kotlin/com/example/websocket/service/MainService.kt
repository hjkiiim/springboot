package com.example.websocket.service

import org.springframework.web.socket.WebSocketSession
import java.util.*
import kotlin.jvm.Throws

interface MainService {

    @Throws(Exception::class)
    fun addSession(session: WebSocketSession)

    @Throws(Exception::class)
    fun removeSession(session: WebSocketSession)

    @Throws(Exception::class)
    fun addEvent(message: String)

}