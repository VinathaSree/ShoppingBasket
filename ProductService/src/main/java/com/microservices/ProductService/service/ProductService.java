package com.microservices.ProductService.service;

import com.microservices.ProductService.exception.ProductServiceCustomException;
import com.microservices.ProductService.model.ProductRequest;
import com.microservices.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long id) throws ProductServiceCustomException;

    void reduceQuantity(long productId, long quantity);
}
