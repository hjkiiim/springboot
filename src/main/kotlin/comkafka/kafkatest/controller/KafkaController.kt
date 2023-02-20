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

    /*@MessageMapping("/message")
    @SendTo("/topic/message")
    fun sendMessage(@Payload message: Message) {
        template.convertAndSend("inbound", message)
    }*/

    @StreamListener(target = "inbound")  // topic
    @PostMapping("/message")          // POST 주소
    @SendTo("/topic/message")           // 모든 topic subscribe user에게 메시지 전송 (SendToUser : 1명에게만)
    fun message(@RequestBody message: Message): String{
        val send = "{\"firstName\":\"" + message.firstName.toString() + "\", \"lastName\":\"" + message.lastName.toString()+"\"}"
//        kafkaTemplate.send("inbound", send)
//        SimpMessagingTemplate : socket에 전달하는 역할 -> 실시간 view 출력 역할
        template.convertAndSend("/topic/message", message)
//        KafkaProducer : 해당하는 topic에 전달하는 역할 -> Consumer에 전달하는 역할
        kafkaProducer.send(send, kafkaTemplate)
        return "Message received : $message"
    }

    @GetMapping("/")
    fun home(modelAndView: ModelAndView): ModelAndView{
//        RestController에서 index.jsp를 불러오기 위한 작업 : viewName 설정
        modelAndView.addObject("text", "text")
        modelAndView.viewName = "index"
        return modelAndView
    }
}