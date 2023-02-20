package comkafka.kafkatest.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumerService {
    @KafkaListener(topics = ["inbound"], groupId = "group-id-hjkim")
    fun consume(message: String): String{
        println("Consumer got message : $message")
        return "message"
    }
}