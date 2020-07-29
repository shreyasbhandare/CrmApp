package com.crmapp.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomerRestExceptionHandler {

    @ExceptionHandler
    fun handleCustomerNotFoundException(exc : CustomerNotFoundException) : ResponseEntity<CustomerErrorResponse> {
        val customerNotFoundExceptionResponse = CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exc.message, System.currentTimeMillis())
        return ResponseEntity(customerNotFoundExceptionResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleGenericException(exc : Exception) : ResponseEntity<CustomerErrorResponse> {
        val genericExceptionResponse = CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.message, System.currentTimeMillis())
        return ResponseEntity(genericExceptionResponse, HttpStatus.BAD_REQUEST)
    }
}