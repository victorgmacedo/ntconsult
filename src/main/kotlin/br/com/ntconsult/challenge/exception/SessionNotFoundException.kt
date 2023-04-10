package br.com.ntconsult.challenge.exception

class SessionNotFoundException(override var message: String): NotFoundException(message = message) {
}