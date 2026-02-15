package com.wms.orderservice.controller;

import com.wms.orderservice.dto.request.ApproveOrderRequest;
import com.wms.orderservice.dto.request.CreateOrderRequest;
import com.wms.orderservice.dto.request.UpdateOrderStatusRequest;
import com.wms.orderservice.dto.response.AvailabilityResponse;
import com.wms.orderservice.dto.response.OrderResponse;
import com.wms.orderservice.entity.OrderStatus;
import com.wms.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Order management endpoints for WMS")
public class OrderController {

    private final OrderService orderService;

    // ---------------------------------------------------------------
    // 1) POST /api/v1/orders — Create order
    // ---------------------------------------------------------------
    @PostMapping
    @Operation(summary = "Create a new order", description = "Creates an order with items. Status starts as CREATED.")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ---------------------------------------------------------------
    // 2) GET /api/v1/orders/{id} — Get order by ID
    // ---------------------------------------------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID id) {
        OrderResponse response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }

    // ---------------------------------------------------------------
    // 3) GET /api/v1/orders?status=APPROVED — List orders
    // ---------------------------------------------------------------
    @GetMapping
    @Operation(summary = "List orders", description = "Returns all orders. Optionally filter by status.")
    public ResponseEntity<List<OrderResponse>> getAllOrders(
            @RequestParam(required = false) OrderStatus status) {
        List<OrderResponse> responses = orderService.getAllOrders(status);
        return ResponseEntity.ok(responses);
    }

    // ---------------------------------------------------------------
    // 4) POST /api/v1/orders/{id}/validate — Validate order
    // ---------------------------------------------------------------
    @PostMapping("/{id}/validate")
    @Operation(summary = "Validate order against inventory",
            description = "Checks availability via Inventory Service. Sets VALIDATED or REJECTED.")
    public ResponseEntity<AvailabilityResponse> validateOrder(@PathVariable UUID id) {
        AvailabilityResponse response = orderService.validateOrder(id);
        return ResponseEntity.ok(response);
    }

    // ---------------------------------------------------------------
    // 5) POST /api/v1/orders/{id}/approve — Approve order
    // ---------------------------------------------------------------
    @PostMapping("/{id}/approve")
    @Operation(summary = "Approve order",
            description = "Approves FULL, PARTIAL, or AUTO. Reserves inventory after approval.")
    public ResponseEntity<OrderResponse> approveOrder(
            @PathVariable UUID id,
            @Valid @RequestBody ApproveOrderRequest request) {
        OrderResponse response = orderService.approveOrder(id, request);
        return ResponseEntity.ok(response);
    }

    // ---------------------------------------------------------------
    // 6) POST /api/v1/orders/{id}/cancel — Cancel order
    // ---------------------------------------------------------------
    @PostMapping("/{id}/cancel")
    @Operation(summary = "Cancel order", description = "Cancels order if not DISPATCHED or DELIVERED.")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable UUID id) {
        OrderResponse response = orderService.cancelOrder(id);
        return ResponseEntity.ok(response);
    }

    // ---------------------------------------------------------------
    // 7) PATCH /api/v1/orders/{id}/status — Update status manually
    // ---------------------------------------------------------------
    @PatchMapping("/{id}/status")
    @Operation(summary = "Update order status", description = "Manual status update with transition validation.")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {
        OrderResponse response = orderService.updateOrderStatus(id, request);
        return ResponseEntity.ok(response);
    }
}
