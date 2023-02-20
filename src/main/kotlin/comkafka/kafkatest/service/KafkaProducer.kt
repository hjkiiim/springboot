package comkafka.kafkatest.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class KafkaProducer {
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    /*fun sendApi(kafkaTemplate: KafkaTemplate<String, String>){
        this.kafkaTemplate = kafkaTemplate
    }*/

    fun send(message: String, kafkaTemplate: KafkaTemplate<String, String>){
        this.kafkaTemplate = kafkaTemplate
        this.kafkaTemplate.send("message", message)
    }
}