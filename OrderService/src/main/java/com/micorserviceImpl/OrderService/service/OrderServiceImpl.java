package com.micorserviceImpl.OrderService.service;

import com.micorserviceImpl.OrderService.entity.Order;
import com.micorserviceImpl.OrderService.exception.CustomException;
import com.micorserviceImpl.OrderService.external.client.PaymentService;
import com.micorserviceImpl.OrderService.external.client.ProductService;
import com.micorserviceImpl.OrderService.external.request.PaymentRequest;
import com.micorserviceImpl.OrderService.model.OrderRequest;
import com.micorserviceImpl.OrderService.model.OrderResponse;
import com.micorserviceImpl.OrderService.repository.OrderRepository;
import com.microserviceImpl.PaymentService.entity.PaymentDetails;
import com.microserviceImpl.PaymentService.model.PaymentResponse;
import com.microservices.ProductService.model.ProductResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductService productService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("order request is received");
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderDate(Instant.now())
                .amount(orderRequest.getTotalAmount())
                .orderStatus("Order created").build();
        orderRepository.save(order);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                        .amount(orderRequest.getTotalAmount())
                                .paymentMode(orderRequest.getPaymentMode().name()).build();
        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("payment got successful");
           orderStatus = "PAYMENT_SUCCESSFUL";
        }catch(Exception e){
            log.error("Exception occurred ");
            orderStatus = "PAYMENT_FAILED";
        }
        order.setOrderStatus(orderStatus);
        log.info("order got created and saved");
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long id) {
     Order order = orderRepository.findById(id)
             .orElseThrow(()->new CustomException("Order with given id is not found","ORDER_NOT_FOUND",404));

     ProductResponse productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(),
             ProductResponse.class);
     OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder().
                productId(productResponse.getProductId())
                .name(productResponse.getName())
                .price(productResponse.getPrice())
                .quantity(productResponse.getQuantity()).build();

     PaymentResponse paymentResponse = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/"+order.getId(),
                PaymentResponse.class);
     OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentStatus(paymentResponse.getStatus())
                .paymentMode(paymentResponse.getPaymentMode())
                .paymentDate(paymentResponse.getPaymentDate())
                .build();

     OrderResponse orderResponse = OrderResponse.builder()
             .orderId(order.getId())
             .orderDate(order.getOrderDate())
             .orderStatus(order.getOrderStatus())
             .amount(order.getAmount()).
             productDetails(productDetails).
             paymentDetails(paymentDetails).build();
     return orderResponse;
    }
}
