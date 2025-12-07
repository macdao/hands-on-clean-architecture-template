package com.example.adapter.persistence.repository;

import com.example.adapter.persistence.entity.OrderEntity;
import com.example.adapter.persistence.mapper.OrderMapper;
import com.example.adapter.persistence.service.OrderService;
import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements SaveOrderPort, FindOrderPort {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Override
    public void save(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId().value());
        entity.setStatus(order.getStatus().name());
        entity.setPrice(order.getPrice().doubleValue());
        orderService.save(entity);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        OrderEntity entity = orderMapper.selectById(orderId.value());
        if (entity == null) {
            return Optional.empty();
        }
        // Convert entity to domain object
        // This would need proper domain object construction
        return Optional.empty(); // Placeholder - full implementation would require domain object factory
    }
}