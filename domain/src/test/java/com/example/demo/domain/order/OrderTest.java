package com.example.demo.domain.order;

import static org.assertj.core.api.Assertions.*;

import com.example.demo.domain.user.UserId;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void new_order_should_have_created_status() {
        UserId buyerId = new UserId("buyer-1");
        Order order = new Order(buyerId, new BigDecimal("100.00"));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    void pay_should_successfully_change_status_to_paid() {
        UserId buyerId = new UserId("buyer-1");
        Order order = new Order(buyerId, new BigDecimal("100.00"));
        order.pay();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PAID);
    }

    @Test
    void pay_should_throw_exception_when_order_not_in_created_state() {
        UserId buyerId = new UserId("buyer-1");
        Order paidOrder = new Order(new OrderId("order-id-1"), buyerId, OrderStatus.PAID, new BigDecimal("100.00"));
        assertThatThrownBy(paidOrder::pay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Order must be in CREATED state to be paid");

        Order cancelledOrder =
                new Order(new OrderId("order-id-2"), buyerId, OrderStatus.CANCELLED, new BigDecimal("100.00"));
        assertThatThrownBy(cancelledOrder::pay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Order must be in CREATED state to be paid");
    }
}
