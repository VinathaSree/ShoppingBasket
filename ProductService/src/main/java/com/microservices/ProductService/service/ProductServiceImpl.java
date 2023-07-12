package com.microservices.ProductService.service;

import com.microservices.ProductService.entity.ProductEntity;
import com.microservices.ProductService.exception.ProductServiceCustomException;
import com.microservices.ProductService.model.ProductRequest;
import com.microservices.ProductService.model.ProductResponse;
import com.microservices.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Product created");
        ProductEntity productEntity = ProductEntity.builder().name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity()).build();
        productRepository.save(productEntity);
        log.info("Product added");
        return productEntity.getProductId();
    }

    @Override
    public ProductResponse getProductById(long id) {
        ProductEntity productEntity = productRepository.findById(id).
                orElseThrow(()->new ProductServiceCustomException("product is not found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(productEntity,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException(
                        "Product with given id is not found",
                        "PRODUCT_NOT_FOUND"));
        if(product.getQuantity() < quantity){
            throw new ProductServiceCustomException("product is insufficient","INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}
