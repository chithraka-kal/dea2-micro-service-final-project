package com.wms.orderservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovedItemRequest {

    @NotBlank(message = "Item ID is required")
    private String itemId;

    @Min(value = 0, message = "Approved quantity cannot be negative")
    private int approvedQty;
}
