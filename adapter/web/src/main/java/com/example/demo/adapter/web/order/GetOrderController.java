package com.example.demo.adapter.web.order;

import java.math.BigDecimal;
import java.time.Instant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public interface GetOrderController {
    @GetMapping("/orders/{orderId}")
    GetOrderResponse getOrder(@PathVariable String orderId);

    record GetOrderResponse(
            String id,
            String buyerId,
            String productId,
            int quantity,
            String status,
            BigDecimal price,
            Instant createdDate,
            Instant lastModifiedDate) {}
}
