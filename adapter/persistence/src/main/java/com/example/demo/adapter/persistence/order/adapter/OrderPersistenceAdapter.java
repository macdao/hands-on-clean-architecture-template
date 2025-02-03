package com.example.demo.adapter.persistence.order.adapter;

import com.example.demo.adapter.persistence.order.OrderEntity;
import com.example.demo.adapter.persistence.order.OrderEntityRepository;
import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.user.UserId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements SaveOrderPort, FindOrderPort {

    private final OrderEntityRepository orderEntityRepository;

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = new OrderEntity(
                order.getId().value(),
                order.getBuyerId().value(),
                order.getProductId().value(),
                order.getQuantity(),
                order.getStatus(),
                order.getPrice(),
                order.getCreatedDate(),
                order.getLastModifiedDate());
        orderEntityRepository.save(orderEntity);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderEntityRepository
                .findById(orderId.value())
                .map(orderEntity -> new Order(
                        new OrderId(orderEntity.getId()),
                        new UserId(orderEntity.getBuyerId()),
                        new ProductId(orderEntity.getProductId()),
                        orderEntity.getQuantity(),
                        orderEntity.getStatus(),
                        orderEntity.getPrice(),
                        orderEntity.getCreatedDate(),
                        orderEntity.getLastModifiedDate()));
    }
}
