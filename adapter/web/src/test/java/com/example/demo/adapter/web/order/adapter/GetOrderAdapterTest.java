package com.example.demo.adapter.web.order.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.demo.adapter.web.order.GetOrderController;
import com.example.demo.application.port.in.GetOrderQuery;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import com.example.demo.domain.order.OrderStatus;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.user.UserId;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrderAdapterTest {
    @Mock
    GetOrderQuery getOrderQuery;

    @InjectMocks
    GetOrderAdapter getOrderAdapter;

    @Test
    void should_get_order() {
        String orderId = "order-id-1";
        Instant now = Instant.now();
        Order order = new Order(
                new OrderId(orderId),
                new UserId("user-id-1"),
                new ProductId("product-id-1"),
                10,
                OrderStatus.CREATED,
                new BigDecimal("100.0"),
                now,
                now);
        when(getOrderQuery.findById(orderId)).thenReturn(order);

        GetOrderController.GetOrderResponse orderResponse = getOrderAdapter.getOrder(orderId);

        assertThat(orderResponse)
                .returns(orderId, from(GetOrderController.GetOrderResponse::id))
                .returns("user-id-1", from(GetOrderController.GetOrderResponse::buyerId))
                .returns("product-id-1", from(GetOrderController.GetOrderResponse::productId))
                .returns(10, from(GetOrderController.GetOrderResponse::quantity))
                .returns("CREATED", from(GetOrderController.GetOrderResponse::status))
                .returns(new BigDecimal("100.0"), from(GetOrderController.GetOrderResponse::price))
                .returns(now, from(GetOrderController.GetOrderResponse::createdDate))
                .returns(now, from(GetOrderController.GetOrderResponse::lastModifiedDate));
    }
}
