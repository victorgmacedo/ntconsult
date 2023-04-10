package br.com.ntconsult.challenge.exception

class SessionAlreadyClosedException(override val message: String) : ApplicationException(message = message)