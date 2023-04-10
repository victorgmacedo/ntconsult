package br.com.ntconsult.challenge.exception

class TopicNotFoundException(override var message: String): NotFoundException(message = message) {
}