package comkafka.kafkatest.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer{

    private val httpHandshakeInterceptor: HttpSessionHandshakeInterceptor? = null

    @Override
    override fun configureMessageBroker(config: MessageBrokerRegistry){
        config.enableSimpleBroker("/topic") // 메시지 발행 요청
        config.setApplicationDestinationPrefixes("/app")    // 메시지 구독 요청
    }

    @Override
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS()
    }
}