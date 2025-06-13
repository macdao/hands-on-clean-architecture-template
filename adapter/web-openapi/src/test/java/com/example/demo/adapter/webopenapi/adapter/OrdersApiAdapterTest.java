package com.example.demo.adapter.webopenapi.adapter;

import com.example.demo.adapter.webopenapi.model.CreateOrderRequest;
import com.example.demo.adapter.webopenapi.model.OrderResponse;
import com.example.demo.application.port.in.GetOrderQuery;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import com.example.demo.domain.order.OrderStatus;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.user.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersApiAdapterTest {
    @Mock
    GetOrderQuery getOrderQuery;

    @InjectMocks
    OrdersApiAdapter ordersApiAdapter;

    @Test
    void getOrder_should_return_order_with_given_id() {
        String orderId = "test-order-id";
        Instant instant = Instant.parse("2025-06-13T23:56:00Z");
        when(getOrderQuery.findById(orderId))
                .thenReturn(new Order(
                        new OrderId(orderId),
                        new UserId("buyer-id-1"),
                        new ProductId("product-id-1"),
                        1,
                        OrderStatus.CREATED,
                        new BigDecimal("100"),
                        instant,
                        instant));

        OrderResponse response = ordersApiAdapter.getOrder(orderId);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(orderId);
    }

    @Test
    void payOrder_should_not_throw_exception() {
        String orderId = "test-order-id";

        ordersApiAdapter.payOrder(orderId);
    }

    @Test
    void placeOrder_should_not_throw_exception() {
        CreateOrderRequest request = new CreateOrderRequest();

        ordersApiAdapter.placeOrder(request);
    }
}
