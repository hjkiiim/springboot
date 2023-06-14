package com.example.websocket.config

import jakarta.servlet.ServletContext
import jakarta.servlet.http.HttpSession
import jakarta.websocket.HandshakeResponse
import jakarta.websocket.server.HandshakeRequest
import jakarta.websocket.server.ServerEndpointConfig
import jakarta.websocket.server.ServerEndpointConfig.Configurator

class HttpSessionConfig: Configurator() {

    val session = "Session"
    val context = "Context"

    @Override
    override fun modifyHandshake(sec: ServerEndpointConfig, request: HandshakeRequest, response: HandshakeResponse) {
        var session: HttpSession = request.httpSession as HttpSession
        var context: ServletContext = session.servletContext

        sec.userProperties.put(this.session, session)
        sec.userProperties.put(this.context, context)
    }
}