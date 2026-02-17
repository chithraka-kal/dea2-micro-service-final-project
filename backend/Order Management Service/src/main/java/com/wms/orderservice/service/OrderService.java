package com.wms.orderservice.service;

import com.wms.orderservice.dto.request.ApproveOrderRequest;
import com.wms.orderservice.dto.request.CreateOrderRequest;
import com.wms.orderservice.dto.request.UpdateOrderStatusRequest;
import com.wms.orderservice.dto.response.AvailabilityResponse;
import com.wms.orderservice.dto.response.OrderResponse;
import com.wms.orderservice.entity.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(UUID id);

    List<OrderResponse> getAllOrders(OrderStatus status);

    AvailabilityResponse validateOrder(UUID id);

    OrderResponse approveOrder(UUID id, ApproveOrderRequest request);

    OrderResponse cancelOrder(UUID id);

    OrderResponse updateOrderStatus(UUID id, UpdateOrderStatusRequest request);
}
