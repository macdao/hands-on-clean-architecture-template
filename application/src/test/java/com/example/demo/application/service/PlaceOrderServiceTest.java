package com.example.demo.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.example.demo.application.port.in.PlaceOrderUseCase.PlaceOrderCommand;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class PlaceOrderServiceTest {

    @Mock
    SaveOrderPort saveOrderPort;

    @Mock
    TransactionTemplate transactionTemplate;

    @InjectMocks
    PlaceOrderService placeOrderService;

    @Captor
    ArgumentCaptor<TransactionCallback<Void>> transactionCallbackCaptor;

    @Captor
    ArgumentCaptor<Order> orderCaptor;

    @Test
    void place_order_should_create_and_save_order() {
        PlaceOrderCommand command = new PlaceOrderCommand("user-id-1", "product-id-1", 1, new BigDecimal("100.0"));

        placeOrderService.placeOrder(command);

        verify(transactionTemplate).execute(transactionCallbackCaptor.capture());

        transactionCallbackCaptor.getValue().doInTransaction(null);
        verify(saveOrderPort).save(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();
        assertThat(capturedOrder.getBuyerId().value()).isEqualTo("user-id-1");
        assertThat(capturedOrder.getPrice()).isEqualByComparingTo(new BigDecimal("100.0"));
        assertThat(capturedOrder.getProductId().value()).isEqualTo("product-id-1");
        assertThat(capturedOrder.getQuantity()).isEqualTo(1);
    }
}
