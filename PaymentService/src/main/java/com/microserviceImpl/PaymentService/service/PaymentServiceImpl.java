package com.microserviceImpl.PaymentService.service;

import com.microserviceImpl.PaymentService.entity.PaymentDetails;
import com.microserviceImpl.PaymentService.model.PaymentMode;
import com.microserviceImpl.PaymentService.model.PaymentRequest;
import com.microserviceImpl.PaymentService.model.PaymentResponse;
import com.microserviceImpl.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    TransactionDetailsRepository repository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("got the payment request");
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .orderId(paymentRequest.getOrderId())
                .paymentMode(paymentRequest.getPaymentMode())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .Date(Instant.now())
                .paymentStatus("Payment done")
                .amount(paymentRequest.getAmount()).build();
        repository.save(paymentDetails);
        log.info("request has been saved");
        return paymentDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetails(String orderId) {
        PaymentDetails paymentDetails = repository.findByOrderId(Long.valueOf(orderId));
        PaymentResponse paymentResponse = PaymentResponse
                .builder()
                .paymentId(paymentDetails.getId())
                .status(paymentDetails.getPaymentStatus())
                .paymentMode(PaymentMode.valueOf(paymentDetails.getPaymentMode()))
                .amount(paymentDetails.getAmount())
                .paymentDate(paymentDetails.getDate())
                .orderId(paymentDetails.getOrderId()).build();
        return paymentResponse;
    }
}
