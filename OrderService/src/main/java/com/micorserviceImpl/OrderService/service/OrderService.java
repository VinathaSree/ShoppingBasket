package com.micorserviceImpl.OrderService.service;

import com.micorserviceImpl.OrderService.model.OrderRequest;
import com.micorserviceImpl.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long id);
}
