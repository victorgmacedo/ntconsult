package br.com.ntconsult.challenge.exception

open class NotFoundException(override val message: String) : ApplicationException(message = message) {
}