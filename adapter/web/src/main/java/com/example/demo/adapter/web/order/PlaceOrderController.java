package com.example.demo.adapter.web.order;

import com.example.demo.adapter.web.order.adapter.PlaceOrderAdapter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlaceOrderController {
    private final PlaceOrderAdapter placeOrderAdapter;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody @Valid PlaceOrderRequest request, @AuthenticationPrincipal UserDetails user) {
        placeOrderAdapter.placeOrder(request, user);
    }

    public record PlaceOrderRequest(
            @NotNull String productId,
            @NotNull @Min(1) Integer quantity,
            @Digits(integer = 6, fraction = 2) BigDecimal price) {}
}
