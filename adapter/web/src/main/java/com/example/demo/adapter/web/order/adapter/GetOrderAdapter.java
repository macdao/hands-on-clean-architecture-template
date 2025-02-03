package com.example.demo.adapter.web.order.adapter;

import com.example.demo.adapter.web.order.GetOrderController;
import com.example.demo.application.port.in.GetOrderQuery;
import com.example.demo.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetOrderAdapter implements GetOrderController.GetOrderHandler {
    private final GetOrderQuery getOrderQuery;

    @Override
    public GetOrderController.GetOrderResponse getOrder(String orderId) {
        Order order = getOrderQuery.findById(orderId);
        return new GetOrderController.GetOrderResponse(
                order.getId().value(),
                order.getBuyerId().value(),
                order.getProductId().value(),
                order.getQuantity(),
                order.getStatus().name(),
                order.getPrice(),
                order.getCreatedDate(),
                order.getLastModifiedDate());
    }
}
