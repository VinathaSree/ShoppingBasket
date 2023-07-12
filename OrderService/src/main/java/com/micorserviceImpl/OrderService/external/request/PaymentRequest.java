package com.micorserviceImpl.OrderService.external.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest {
    private long orderId;
    private long amount;
    private String referenceNumber;
    private String paymentMode;
}
