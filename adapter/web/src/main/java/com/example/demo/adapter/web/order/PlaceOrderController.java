package com.example.demo.adapter.web.order;

import com.example.demo.application.port.in.PlaceOrderUseCase;
import com.example.demo.application.port.in.PlaceOrderUseCase.PlaceOrderCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlaceOrderController {
    private final PlaceOrderUseCase placeOrderUseCase;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody @Valid PlaceOrderRequest request, @AuthenticationPrincipal User user) {
        PlaceOrderCommand command =
                new PlaceOrderCommand(user.getUsername(), request.productId(), request.quantity(), request.price());
        placeOrderUseCase.placeOrder(command);
    }

    public record PlaceOrderRequest(
            @NotNull String productId,
            @NotNull @Min(1) Integer quantity,
            @Digits(integer = 6, fraction = 2) BigDecimal price) {}
}
