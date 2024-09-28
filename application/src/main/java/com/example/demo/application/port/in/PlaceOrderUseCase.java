package com.example.demo.application.port.in;

import java.math.BigDecimal;

public interface PlaceOrderUseCase {
    void placeOrder(PlaceOrderCommand command);

    public record PlaceOrderCommand(String buyerId, BigDecimal price) {}
}
