package com.example.demo.adapter.web.order.adapter;

import com.example.demo.adapter.web.order.PlaceOrderController;
import com.example.demo.application.port.in.PlaceOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Component
public class PlaceOrderAdapter implements PlaceOrderController {
    private final PlaceOrderUseCase placeOrderUseCase;

    @Override
    public void placeOrder(
            @RequestBody @Valid PlaceOrderController.PlaceOrderRequest request,
            @AuthenticationPrincipal UserDetails user) {
        PlaceOrderUseCase.PlaceOrderCommand command = new PlaceOrderUseCase.PlaceOrderCommand(
                user.getUsername(), request.productId(), request.quantity(), request.price());
        placeOrderUseCase.placeOrder(command);
    }
}
