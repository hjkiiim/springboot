package comkafka.kafkatest.config

import comkafka.kafkatest.service.IBrokerChannel
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Configuration
@Configuration
@EnableBinding(IBrokerChannel::class)
class BrokerIntegrationConfig {
}