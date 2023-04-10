package br.com.ntconsult.challenge.exception

import br.com.ntconsult.challenge.domain.ApplicationErrorDetail
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class ExceptionHandle: ResponseEntityExceptionHandler() {

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(applicationException: ApplicationException): ResponseEntity<ApplicationErrorDetail> {
        val apiErrorMessage = ApplicationErrorDetail(applicationException.message)
        return ResponseEntity.badRequest().body(apiErrorMessage)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(notFoundException: NotFoundException): ResponseEntity<ApplicationErrorDetail> {
        val apiErrorMessage = ApplicationErrorDetail(notFoundException.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorMessage)
    }

}