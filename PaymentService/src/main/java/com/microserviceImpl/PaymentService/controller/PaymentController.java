package com.microserviceImpl.PaymentService.controller;

import com.microserviceImpl.PaymentService.model.PaymentRequest;
import com.microserviceImpl.PaymentService.model.PaymentResponse;
import com.microserviceImpl.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        long id = paymentService.doPayment(paymentRequest);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentResponse(@PathVariable String orderId){
        PaymentResponse paymentResponse = paymentService.getPaymentDetails(orderId);
        return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
    }
}
