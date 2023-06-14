package comkafka.kafkatest.service

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel
interface IBrokerChannel {
    @Input("inbound")
    fun inbound(): SubscribableChannel
}