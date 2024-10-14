package com.example.demo.application.port.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;

@Validated
public interface PlaceOrderUseCase {
    void placeOrder(@Valid PlaceOrderCommand command);

    record PlaceOrderCommand(
            @NotNull String buyerId,
            @NotNull String productId,
            @NotNull @Min(1) Integer quantity,
            @NotNull BigDecimal price) {}
}
