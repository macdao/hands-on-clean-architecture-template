package com.example.demo.adapter.web.order;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GetOrderController {
    private final GetOrderHandler getOrderHandler;

    @GetMapping("/orders/{orderId}")
    public GetOrderResponse getOrder(@PathVariable String orderId) {
        return getOrderHandler.getOrder(orderId);
    }

    public record GetOrderResponse(
            String id,
            String buyerId,
            String productId,
            int quantity,
            String status,
            BigDecimal price,
            Instant createdDate,
            Instant lastModifiedDate) {}

    public interface GetOrderHandler {
        GetOrderResponse getOrder(@PathVariable String orderId);
    }
}
