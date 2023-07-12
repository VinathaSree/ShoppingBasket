package com.microserviceImpl.PaymentService.repository;

import com.microserviceImpl.PaymentService.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<PaymentDetails,Long> {
    PaymentDetails findByOrderId(long orderId);
}
