package com.example.demo.adapter.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import jakarta.validation.ConstraintViolationException;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public abstract class OrdersBase extends ContractTestBase {

    @BeforeEach
    public void setup() {
        super.setup();

        doThrow(new IllegalStateException()).when(payOrderUseCase).payOrder("order-id-2");
        doThrow(new ConstraintViolationException("Invalid order", null))
                .when(placeOrderAdapter)
                .placeOrder(argThat(command -> command.price() == null), argThat(user -> user.getUsername()
                        .equals("user-token")));

        doThrow(new RuntimeException("Unexpected error")).when(payOrderUseCase).payOrder("order-id-3");
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        if (testInfo.getTestMethod()
                .filter(method -> method.getName().equals("validate_place_a_new_order"))
                .isPresent()) {
            verify(placeOrderAdapter)
                    .placeOrder(
                            assertArg(request -> {
                                assertThat(request.productId()).isEqualTo("product-id-1");
                                assertThat(request.quantity()).isEqualTo(1);
                                assertThat(request.price()).isEqualTo(new BigDecimal("100.0"));
                            }),
                            argThat(user -> user.getUsername().equals("user-token")));
        }
    }
}
