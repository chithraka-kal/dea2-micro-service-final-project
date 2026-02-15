package com.wms.orderservice.service.client;

import com.wms.orderservice.dto.response.AvailabilityResponse;
import com.wms.orderservice.exception.ExternalServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryClient {

    private final RestClient inventoryRestClient;

    /**
     * Check inventory availability for a given order.
     * GET /inventory/availability?orderId={uuid}
     */
    public AvailabilityResponse checkAvailability(UUID orderId) {
        try {
            log.debug("Checking inventory availability for order: {}", orderId);
            AvailabilityResponse response = inventoryRestClient.get()
                    .uri("/inventory/availability?orderId={orderId}", orderId)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(AvailabilityResponse.class);

            if (response == null) {
                throw new ExternalServiceException("Received null response from Inventory Service for order: " + orderId);
            }

            log.debug("Inventory availability result - canFulfill: {}, missingItems: {}",
                    response.isCanFulfill(),
                    response.getMissingItems() != null ? response.getMissingItems().size() : 0);

            return response;
        } catch (RestClientException ex) {
            log.error("Failed to check inventory availability for order: {}", orderId, ex);
            throw new ExternalServiceException("Inventory Service availability check failed: " + ex.getMessage(), ex);
        }
    }

    /**
     * Reserve inventory for approved order items.
     * POST /inventory/reservations
     */
    public void reserveInventory(UUID orderId, List<ReserveItem> items) {
        try {
            log.debug("Reserving inventory for order: {}, items count: {}", orderId, items.size());

            Map<String, Object> requestBody = Map.of(
                    "orderId", orderId.toString(),
                    "items", items
            );

            inventoryRestClient.post()
                    .uri("/inventory/reservations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(requestBody)
                    .retrieve()
                    .toBodilessEntity();

            log.debug("Inventory reservation successful for order: {}", orderId);
        } catch (RestClientException ex) {
            log.error("Failed to reserve inventory for order: {}", orderId, ex);
            throw new ExternalServiceException("Inventory Service reservation failed: " + ex.getMessage(), ex);
        }
    }

    /**
     * DTO for inventory reservation item.
     */
    public record ReserveItem(String itemId, int quantity) {}
}
