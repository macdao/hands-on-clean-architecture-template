package com.example.demo.adapter.persistence.order;

import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements SaveOrderPort, FindOrderPort {

    private final OrderEntityRepository orderEntityRepository;

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = new OrderEntity(
            order.getId().value(),
            order.getStatus(),
            order.getPrice()
        );
        orderEntityRepository.save(orderEntity);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderEntityRepository.findById(orderId.value())
            .map(orderEntity -> new Order(
                new OrderId(orderEntity.getId()),
                orderEntity.getStatus(),
                orderEntity.getPrice()
            ));
    }
}