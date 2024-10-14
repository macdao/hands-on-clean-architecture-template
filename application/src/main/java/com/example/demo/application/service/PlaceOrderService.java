package com.example.demo.application.service;

import com.example.demo.application.port.in.PlaceOrderUseCase;
import com.example.demo.application.port.out.DeductInventoryPort;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {
    private final SaveOrderPort saveOrderPort;
    private final DeductInventoryPort deductInventoryPort;
    private final TransactionTemplate transactionTemplate;

    @Override
    public void placeOrder(PlaceOrderCommand command) {
        deductInventoryPort.deductInventory(command.productId(), command.quantity());

        transactionTemplate.execute(status -> {
            Order order = new Order(
                    new UserId(command.buyerId()),
                    new ProductId(command.productId()),
                    command.quantity(),
                    command.price());

            saveOrderPort.save(order);
            return null;
        });
    }
}
