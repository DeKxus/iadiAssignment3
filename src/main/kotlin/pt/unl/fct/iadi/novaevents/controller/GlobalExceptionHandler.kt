package pt.unl.fct.iadi.novaevents.controller

import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(e: NoSuchElementException, model: Model): String {
        model.addAttribute("message", e.message)
        // Only show stack trace in dev mode
        val stackTrace = e.stackTrace.joinToString("\n")
        model.addAttribute("stackTrace", stackTrace)

        return "error/404"
    }

}