package com.wms.orderservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "partialAllowed flag is required")
    private Boolean partialAllowed;

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<CreateOrderItemRequest> items;
}
