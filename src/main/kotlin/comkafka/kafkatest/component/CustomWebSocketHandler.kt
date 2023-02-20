package comkafka.kafkatest.component

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Component
class CustomWebSocketHandler {

    @Override
    fun handleTextMessage(session: WebSocketSession, message: TextMessage){
        val payload = message.payload
        

    }
}