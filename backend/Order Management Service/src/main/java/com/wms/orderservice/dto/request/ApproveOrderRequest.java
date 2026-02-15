package com.wms.orderservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApproveOrderRequest {

    public enum ApprovalType {
        FULL, PARTIAL, AUTO
    }

    @NotNull(message = "Approval type is required")
    private ApprovalType approvalType;

    @Valid
    private List<ApprovedItemRequest> approvedItems;
}
