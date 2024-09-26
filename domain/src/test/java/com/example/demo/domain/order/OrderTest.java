package com.example.demo.domain.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void new_rder_should_have_created_status() {
        Order order = new Order(new BigDecimal("100.00"));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    void pay_should_successfully_change_status_to_paid() {
        Order order = new Order(new BigDecimal("100.00"));
        order.pay();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PAID);
    }

    @Test
    void pay_should_throw_exception_when_order_not_in_created_state() {
        Order paidOrder = new Order(new OrderId("order-id-1"), OrderStatus.PAID, new BigDecimal("100.00"));
        assertThatThrownBy(paidOrder::pay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Order must be in CREATED state to be paid");

        Order cancelledOrder = new Order(new OrderId("order-id-2"), OrderStatus.CANCELLED, new BigDecimal("100.00"));
        assertThatThrownBy(cancelledOrder::pay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Order must be in CREATED state to be paid");
    }
}
