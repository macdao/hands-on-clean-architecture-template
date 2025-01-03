package com.example.demo.adapter.persistence.order;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import com.example.demo.domain.order.OrderStatus;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.user.UserId;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderPersistenceAdapterTest {

    @Autowired
    private OrderPersistenceAdapter orderPersistenceAdapter;

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Test
    void save_should_save_order_entity() {
        UserId buyerId = new UserId("buyer-id-1");
        Order order = new Order(buyerId, new ProductId("product-id-1"), 1, new BigDecimal("100.00"));
        OrderId orderId = order.getId();

        orderPersistenceAdapter.save(order);

        OrderEntity savedEntity =
                orderEntityRepository.findById(orderId.value()).orElseThrow();
        assertThat(savedEntity.getId()).isEqualTo(orderId.value());
        assertThat(savedEntity.getBuyerId()).isEqualTo(buyerId.value());
        assertThat(savedEntity.getProductId()).isEqualTo("product-id-1");
        assertThat(savedEntity.getQuantity()).isEqualTo(1);
        assertThat(savedEntity.getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(savedEntity.getPrice()).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    @Sql("/sqls/orders.sql")
    void find_by_id_should_return_order() {
        OrderId orderId = new OrderId("order-id-1");

        Order foundOrder = orderPersistenceAdapter.findById(orderId).orElseThrow();

        assertThat(foundOrder).isNotNull();
        assertThat(foundOrder.getId()).isEqualTo(orderId);
        assertThat(foundOrder.getBuyerId()).isEqualTo(new UserId("buyer-id-1"));
        assertThat(foundOrder.getProductId()).isEqualTo(new ProductId("product-id-1"));
        assertThat(foundOrder.getQuantity()).isEqualTo(1);
        assertThat(foundOrder.getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(foundOrder.getPrice()).isEqualByComparingTo(new BigDecimal("100.00"));
    }
}
