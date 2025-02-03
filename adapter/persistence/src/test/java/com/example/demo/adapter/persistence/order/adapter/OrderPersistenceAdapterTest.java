package com.example.demo.adapter.persistence.order.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.Mockito.*;

import com.example.demo.adapter.persistence.order.OrderEntity;
import com.example.demo.adapter.persistence.order.OrderEntityRepository;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import com.example.demo.domain.order.OrderStatus;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.user.UserId;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderPersistenceAdapterTest {

    @Mock
    private OrderEntityRepository orderEntityRepository;

    @InjectMocks
    private OrderPersistenceAdapter orderPersistenceAdapter;

    @Test
    void save_should_success() {
        Instant now = Instant.now();
        Order order = new Order(
                new OrderId("1"),
                new UserId("buyer1"),
                new ProductId("product1"),
                2,
                OrderStatus.CREATED,
                new BigDecimal("100.0"),
                now,
                now);

        orderPersistenceAdapter.save(order);

        verify(orderEntityRepository).save(assertArg(orderEntity -> {
            assertThat(orderEntity)
                    .returns("1", from(OrderEntity::getId))
                    .returns("buyer1", from(OrderEntity::getBuyerId))
                    .returns("product1", from(OrderEntity::getProductId))
                    .returns(2, from(OrderEntity::getQuantity))
                    .returns(OrderStatus.CREATED, from(OrderEntity::getStatus))
                    .returns(new BigDecimal("100.0"), from(OrderEntity::getPrice))
                    .returns(now, from(OrderEntity::getCreatedDate))
                    .returns(now, from(OrderEntity::getLastModifiedDate));
        }));
    }

    @Test
    void find_by_id_should_success() {
        Instant now = Instant.now();
        OrderEntity orderEntity =
                new OrderEntity("1", "buyer1", "product1", 2, OrderStatus.CREATED, new BigDecimal("100.0"), now, now);

        when(orderEntityRepository.findById("1")).thenReturn(Optional.of(orderEntity));

        Optional<Order> result = orderPersistenceAdapter.findById(new OrderId("1"));

        assertThat(result).isPresent();
        assertThat(result.get().getId().value()).isEqualTo("1");
        assertThat(result.get().getBuyerId().value()).isEqualTo("buyer1");
        assertThat(result.get().getProductId().value()).isEqualTo("product1");
        assertThat(result.get().getQuantity()).isEqualTo(2);
        assertThat(result.get().getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(result.get().getPrice()).isEqualByComparingTo(new BigDecimal("100.0"));
    }
}
