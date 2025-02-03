package com.example.demo.adapter.web.order;

import com.example.demo.application.port.in.PayOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PayOrderController {
    private final PayOrderUseCase payOrderUseCase;

    @PostMapping("/orders/{orderId}/pay")
    public void payOrder(@PathVariable String orderId) {
        payOrderUseCase.payOrder(orderId);
    }
}
