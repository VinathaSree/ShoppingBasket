package com.microserviceImpl.PaymentService.service;

import com.microserviceImpl.PaymentService.model.PaymentRequest;
import com.microserviceImpl.PaymentService.model.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetails(String orderId);
}
