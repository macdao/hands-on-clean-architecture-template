package com.example.demo.adapter.web.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PayOrderController {
    private final PayOrderHandler payOrderHandler;

    @PostMapping("/orders/{orderId}/pay")
    public void payOrder(@PathVariable String orderId) {
        payOrderHandler.payOrder(orderId);
    }

    public interface PayOrderHandler {
        void payOrder(String orderId);
    }
}
