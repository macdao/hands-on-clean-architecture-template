package com.example.demo.adapter.webopenapi.adapter;

import com.example.demo.adapter.webopenapi.api.OrdersApi;
import com.example.demo.adapter.webopenapi.model.CreateOrderRequest;
import com.example.demo.adapter.webopenapi.model.OrderResponse;
import com.example.demo.application.port.in.GetOrderQuery;
import com.example.demo.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrdersApiAdapter implements OrdersApi {
    private final GetOrderQuery getOrderQuery;

    @Override
    public OrderResponse getOrder(String orderId) {
        Order order = getOrderQuery.findById(orderId);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId().value());
        orderResponse.setBuyerId(order.getBuyerId().value());
        return orderResponse;
    }

    @Override
    public void payOrder(String orderId) {}

    @Override
    public void placeOrder(CreateOrderRequest createOrderRequest) {}
}
