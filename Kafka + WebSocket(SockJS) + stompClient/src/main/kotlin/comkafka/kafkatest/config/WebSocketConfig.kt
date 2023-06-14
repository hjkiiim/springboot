package comkafka.kafkatest.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer{

    @Override
    override fun configureMessageBroker(config: MessageBrokerRegistry){
        config.enableSimpleBroker("/topic") // 메시지 발행 요청
        config.setApplicationDestinationPrefixes("/app")    // 메시지 구독 요청
    }

    @Override
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
//        subscribe 과정에서 오류 발생할 수 있으므로, setAllowedOriginPatterns("*") 사용하면 정상적으로 Subscribe됨.
        registry.addEndpoint("/websocket").setAllowedOriginPatterns("*").withSockJS()
    }
}