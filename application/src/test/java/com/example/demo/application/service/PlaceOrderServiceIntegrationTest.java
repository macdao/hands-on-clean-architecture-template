package com.example.demo.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.demo.application.port.in.PlaceOrderUseCase.PlaceOrderCommand;
import jakarta.validation.ConstraintViolationException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PlaceOrderServiceIntegrationTest extends IntegrationTestBase {

    @Autowired
    PlaceOrderService placeOrderService;

    @Test
    void place_order_should_create_and_save_order() {
        PlaceOrderCommand command = new PlaceOrderCommand("user-id", new BigDecimal("100.0"));

        placeOrderService.placeOrder(command);
    }

    @Test
    void place_order_should_throw_exception_when_price_is_null() {
        PlaceOrderCommand command = new PlaceOrderCommand("user-id", null);

        assertThatThrownBy(() -> placeOrderService.placeOrder(command))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
