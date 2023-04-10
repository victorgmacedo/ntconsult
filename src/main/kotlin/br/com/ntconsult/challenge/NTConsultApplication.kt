package br.com.ntconsult.challenge

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableAutoConfiguration
class NTConsultApplication

fun main(args: Array<String>) {
	runApplication<NTConsultApplication>(*args)
}
