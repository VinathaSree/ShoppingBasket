package com.micorserviceImpl.OrderService.controller;

import com.micorserviceImpl.OrderService.model.OrderRequest;
import com.micorserviceImpl.OrderService.model.OrderResponse;
import com.micorserviceImpl.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    private ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long id){
        OrderResponse orderResponse = orderService.getOrderDetails(id);
        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }
}
