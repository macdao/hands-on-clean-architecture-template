package com.example.demo.adapter.persistence.order;

import static com.example.demo.domain.order.OrderStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.domain.order.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
class OrderEntityRepositoryTest {

    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Test
    void save_should_save_order_entity() {
        String entityId = "an-order-id";
        String buyerId = "buyer-id-1";
        String productId = "product-id-1";
        int quantity = 1;
        OrderStatus orderStatus = CREATED;
        BigDecimal price = new BigDecimal("100.00");
        var orderEntity = new OrderEntity(
                entityId, buyerId, productId, quantity, orderStatus, price, Instant.now(), Instant.now());

        orderEntityRepository.save(orderEntity);

        OrderEntity savedEntity = orderEntityRepository.findById(entityId).orElseThrow();
        assertThat(savedEntity.getId()).isEqualTo(entityId);
        assertThat(savedEntity.getBuyerId()).isEqualTo(buyerId);
        assertThat(savedEntity.getProductId()).isEqualTo(productId);
        assertThat(savedEntity.getQuantity()).isEqualTo(quantity);
        assertThat(savedEntity.getStatus()).isEqualTo(orderStatus);
        assertThat(savedEntity.getPrice()).isEqualByComparingTo(price);
    }

    @Test
    @Sql("/sqls/orders.sql")
    void find_by_id_should_return_order() {
        String orderId = "order-id-1";

        OrderEntity orderEntity = orderEntityRepository.findById(orderId).orElseThrow();

        assertThat(orderEntity).isNotNull();
        assertThat(orderEntity.getId()).isEqualTo(orderId);
        assertThat(orderEntity.getBuyerId()).isEqualTo("buyer-id-1");
        assertThat(orderEntity.getProductId()).isEqualTo("product-id-1");
        assertThat(orderEntity.getQuantity()).isEqualTo(1);
        assertThat(orderEntity.getStatus()).isEqualTo(CREATED);
        assertThat(orderEntity.getPrice()).isEqualByComparingTo(new BigDecimal("100.00"));
    }
}
