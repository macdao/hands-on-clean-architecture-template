package com.example.demo.adapter.web.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public interface PayOrderController {
    @PostMapping("/orders/{orderId}/pay")
    void payOrder(@PathVariable String orderId);
}
