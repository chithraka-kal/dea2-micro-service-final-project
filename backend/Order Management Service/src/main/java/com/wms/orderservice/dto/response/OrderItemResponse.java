package com.wms.orderservice.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {

    private UUID id;
    private String itemId;
    private int requestedQty;
    private int approvedQty;
    private BigDecimal unitPrice;
}
