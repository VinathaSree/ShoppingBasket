package com.microservices.ProductService.exception;

import com.microservices.ProductService.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductServiceCustomException productNotFoundException){
        return new ResponseEntity<>(new ErrorMessage().builder()
                .errorMessage(productNotFoundException.getMessage())
                .errorCode(productNotFoundException.getErrorCode()).build(), HttpStatus.NOT_FOUND);
    }
}
