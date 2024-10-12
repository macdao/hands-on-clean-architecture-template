package com.example.demo.adapter.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.example.demo.application.port.in.PlaceOrderUseCase;
import jakarta.validation.ConstraintViolationException;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

public abstract class OrdersBase extends ContractTestBase {
    @Captor
    ArgumentCaptor<PlaceOrderUseCase.PlaceOrderCommand> placeOrderCommandArgumentCaptor;

    @BeforeEach
    public void setup() {
        super.setup();

        doThrow(new IllegalStateException()).when(payOrderUseCase).payOrder("order-id-2");
        doThrow(new ConstraintViolationException("Invalid order", null))
                .when(placeOrderUseCase)
                .placeOrder(argThat(command -> command.price() == null));

        doThrow(new RuntimeException("Unexpected error")).when(payOrderUseCase).payOrder("order-id-3");
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        if (testInfo.getTestMethod()
                .filter(method -> method.getName().equals("validate_place_a_new_order"))
                .isPresent()) {
            verify(placeOrderUseCase).placeOrder(placeOrderCommandArgumentCaptor.capture());
            PlaceOrderUseCase.PlaceOrderCommand command = placeOrderCommandArgumentCaptor.getValue();
            assertThat(command.price()).isEqualTo(new BigDecimal("100.0"));
            assertThat(command.buyerId()).isEqualTo("user-token");
        }
    }
}
