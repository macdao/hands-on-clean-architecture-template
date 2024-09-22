package com.example.demo.application.service;

import org.springframework.stereotype.Service;

import com.example.demo.application.port.in.GetOrderQuery;
import com.example.demo.application.port.in.OrderNotFoundException;
import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetOrderService implements GetOrderQuery {
    private final FindOrderPort findOrderPort;

    @Override
    public Order findById(String orderId) throws OrderNotFoundException {
        return findOrderPort.findById(new OrderId(orderId))
            .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }
}