package com.microservices.ProductService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    @Column(name = "PRODUCT_NAME")
    private String name;
    @Column(name = "PRICE")
    private long price;
    @Column(name = "QUANTITY")
    private long quantity;
}
