package com.example.demo.application.service;

import com.example.demo.application.port.in.PayOrderUseCase;
import com.example.demo.application.port.in.OrderNotFoundException;
import com.example.demo.domain.order.OrderId;
import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayOrderService implements PayOrderUseCase {
    private final FindOrderPort findOrderPort;
    private final SaveOrderPort saveOrderPort;

    @Override
    @Transactional
    public void payOrder(String orderId) throws OrderNotFoundException {
        OrderId orderIdObj = new OrderId(orderId);
        Order order = findOrderPort.findById(orderIdObj)
            .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderId + " not found"));

        order.pay();

        saveOrderPort.save(order);
    }
}