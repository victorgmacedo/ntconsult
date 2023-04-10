package br.com.ntconsult.challenge.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfiguration {

    @Bean
    fun queue(): Queue {
        return Queue(QUEUE_NAME, false)
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(TOPIC_EXCHANGE_NAME)
    }

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("session.result")
    }

    companion object {
        const val QUEUE_NAME = "session.result"
        const val TOPIC_EXCHANGE_NAME = "session.result.exchange"
    }

}