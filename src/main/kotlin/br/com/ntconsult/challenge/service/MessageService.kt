package br.com.ntconsult.challenge.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(private val rabbitTemplate: RabbitTemplate,
                     private val objectMapper: ObjectMapper) {

    fun publishMessage(routeKey:String, message: Any) {
        rabbitTemplate.convertAndSend(routeKey, objectMapper.writeValueAsString(message))
    }

}