package com.wms.orderservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissingItemResponse {

    private String itemId;
    private int missingQty;
}
