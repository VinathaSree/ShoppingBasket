package com.microservices.ProductService.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException {
    String errorCode;
    public ProductServiceCustomException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
