package com.wms.orderservice.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilityResponse {

    private boolean canFulfill;
    private List<MissingItemResponse> missingItems;
    private List<SuggestedApprovedItem> suggestedApprovedItems;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SuggestedApprovedItem {
        private String itemId;
        private int approvedQty;
    }
}
