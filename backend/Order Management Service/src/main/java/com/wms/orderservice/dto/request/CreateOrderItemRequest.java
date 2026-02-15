package com.wms.orderservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderItemRequest {

    @NotBlank(message = "Item ID is required")
    private String itemId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    private BigDecimal unitPrice;
}
