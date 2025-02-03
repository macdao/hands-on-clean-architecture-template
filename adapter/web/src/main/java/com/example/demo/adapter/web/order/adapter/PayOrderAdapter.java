package com.example.demo.adapter.web.order.adapter;

import com.example.demo.adapter.web.order.PayOrderController;
import com.example.demo.application.port.in.PayOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayOrderAdapter implements PayOrderController.PayOrderHandler {
    private final PayOrderUseCase payOrderUseCase;

    @Override
    public void payOrder(String orderId) {
        payOrderUseCase.payOrder(orderId);
    }
}
