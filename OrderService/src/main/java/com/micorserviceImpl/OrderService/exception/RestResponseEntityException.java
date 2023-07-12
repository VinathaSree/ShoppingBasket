package com.micorserviceImpl.OrderService.exception;

import com.micorserviceImpl.OrderService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException customException){
        return new ResponseEntity<>(new ErrorResponse().builder().errorMessage(customException.getMessage())
                .errorCode(customException.getErrorCode()).build(),
                HttpStatus.valueOf(customException.getStatus()));
    }
}
