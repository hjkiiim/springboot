package comkafka.kafkatest.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class Listener {
    @Autowired
    private lateinit var template: SimpMessagingTemplate
    @StreamListener(target = "inbound")
    fun processMessage(pushMessage: Message) {
        template.convertAndSend("/topic/message", pushMessage)
    }
}