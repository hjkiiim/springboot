package comkafka.kafkatest.controller

import comkafka.kafkatest.model.Message
import comkafka.kafkatest.service.KafkaProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView


@RestController
class KafkaController {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>
    @Autowired
    private lateinit var kafkaProducer: KafkaProducer
    @Autowired
    private lateinit var template: SimpMessagingTemplate

    @MessageMapping("message")
    @SendTo("/topic/message")
    fun sendMessage(@Payload message: Message): Message {
        println("*** Message : $message")
        return message
    }
    @StreamListener(target = "inbound")
    @PostMapping(value = ["/websocket"])
    fun message(@RequestBody message: Message): String{
        val send = "{\"firstName\":\"" + message.firstName.toString() + "\", \"lastName\":\"" + message.lastName.toString()+"\"}"
        kafkaTemplate.send("inbound", send)
        template.convertAndSend("/topic/message", message)
        kafkaProducer.send(send, kafkaTemplate)
        println("*** send : " + send)
        return "Message received : $message"
    }

    @RequestMapping("/")
    fun home(modelAndView: ModelAndView): ModelAndView{
        modelAndView.addObject("text", "text")
        modelAndView.viewName = "index"
        return modelAndView
    }
}