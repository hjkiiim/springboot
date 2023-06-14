package com.example.websocket.config

import com.example.websocket.handler.WebSocketHandler
import com.example.websocket.websocket.WebSocketHandShakeInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor

@Configuration  // 설정파일 의미
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {

    private val webSocketHandler = WebSocketHandler()
    @Override
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(getHandler(), "/ws").setAllowedOriginPatterns("*")
            .addInterceptors(HttpSessionHandshakeInterceptor(), WebSocketHandShakeInterceptor())
    }

    @Bean
    fun getHandler(): WebSocketHandler{
        return webSocketHandler
    }
}