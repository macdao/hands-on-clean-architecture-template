package com.example.demo.application.port.in;

public interface PayOrderUseCase {
    void payOrder(String orderId) throws OrderNotFoundException;
}