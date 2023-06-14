package com.example.websocket.websocket

import jakarta.servlet.http.HttpSession
import jakarta.servlet.jsp.PageContext.SESSION
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor

class WebSocketHandShakeInterceptor() : HandshakeInterceptor {
    @Override
    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        val servletRequest: ServletServerHttpRequest = request as ServletServerHttpRequest
        val session: HttpSession = servletRequest.servletRequest.session
        attributes[SESSION] = session
        return true
    }

    @Override
    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        exception: Exception?
    ) {
    }
}