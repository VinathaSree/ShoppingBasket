package com.microservices.ProductService.model;

import lombok.Data;

@Data
public class ProductResponse {
    private long productId;
    private String name;
    private long price;
    private long quantity;
}
