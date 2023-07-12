package com.micorserviceImpl.OrderService.external.decoder;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micorserviceImpl.OrderService.exception.CustomException;
import com.micorserviceImpl.OrderService.model.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            ErrorResponse errorResponse =
                    objectMapper.readValue(response.body().asInputStream(),ErrorResponse.class);
            return new CustomException(errorResponse.getErrorMessage(),errorResponse.getErrorCode(), response.status());
        } catch (IOException e) {
            throw new CustomException("Quantity is less","INSUFFICIENT_QUANTITY",500);
        }
    }
}
