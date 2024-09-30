package com.example.demo.adapter.web;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;

public abstract class OrdersBase extends ContractTestBase {
    @BeforeEach
    public void setup() {
        super.setup();

        doThrow(new IllegalStateException()).when(payOrderUseCase).payOrder("order-id-2");
        doThrow(new ConstraintViolationException("Invalid order", null))
                .when(placeOrderUseCase)
                .placeOrder(argThat(command -> command.price() == null));

        doThrow(new RuntimeException("Unexpected error")).when(payOrderUseCase).payOrder("order-id-3");
    }
}
