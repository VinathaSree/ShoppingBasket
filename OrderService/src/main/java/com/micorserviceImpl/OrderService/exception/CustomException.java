package com.micorserviceImpl.OrderService.exception;

import lombok.Data;

import java.util.function.Supplier;

@Data
public class CustomException extends RuntimeException {

    String errorCode;
    int status;
    public CustomException(String message,String errorCode,int status){
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
