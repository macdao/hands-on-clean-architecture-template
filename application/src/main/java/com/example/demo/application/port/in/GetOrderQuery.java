package com.example.demo.application.port.in;

import com.example.demo.domain.order.Order;

public interface GetOrderQuery {
    Order findById(String orderId) throws OrderNotFoundException;
}