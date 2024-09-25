package com.example.demo.adapter.web;

import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.BeforeEach;

public abstract class OrdersBase extends ContractTestBase {
    @BeforeEach
    public void setup() {
        super.setup();

        doThrow(new IllegalStateException()).when(payOrderUseCase).payOrder("order-id-2");
    }
}
