package com.microserviceImpl.PaymentService.entity;

import com.microserviceImpl.PaymentService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="Payment_Details")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="ORDER_ID")
    private long orderId;
    @Column(name="PAYMENT_MODE")
    private String paymentMode;
    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;
    @Column(name="DATE")
    private Instant Date;
    @Column(name="PAYMENT_STATUS")
    private String paymentStatus;
    @Column(name="AMOUNT")
    private long amount;
}
