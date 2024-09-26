package com.example.demo.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.application.port.in.OrderNotFoundException;
import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.application.port.out.SaveOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.SimpleTransactionStatus;

@SpringBootTest
public class PayOrderServiceIntegrationTest {
    @Autowired
    PlatformTransactionManager transactionManager;

    @MockBean
    FindOrderPort findOrderPort;

    @MockBean
    SaveOrderPort saveOrderPort;

    @Autowired
    PayOrderService payOrderService;

    @AfterEach
    void tearDown() {
        reset(transactionManager);
    }

    @Test
    void pay_order_should_pay_and_save_order_when_order_exists() throws OrderNotFoundException {
        String orderId = "order-id-1";
        Order mockOrder = mock(Order.class);
        when(findOrderPort.findById(new OrderId(orderId))).thenReturn(Optional.of(mockOrder));
        when(transactionManager.getTransaction(any())).thenReturn(new SimpleTransactionStatus());

        payOrderService.payOrder(orderId);

        verify(mockOrder).pay();
        verify(saveOrderPort).save(mockOrder);
        verify(transactionManager).commit(any());
    }

    @Test
    void pay_order_should_throw_order_not_found_exception_when_order_does_not_exist() throws OrderNotFoundException {
        String orderId = "order-id-1";
        when(findOrderPort.findById(new OrderId(orderId))).thenReturn(Optional.empty());
        when(transactionManager.getTransaction(any())).thenReturn(new SimpleTransactionStatus());

        assertThatThrownBy(() -> payOrderService.payOrder(orderId)).isInstanceOf(OrderNotFoundException.class);
        verify(transactionManager).rollback(any());
    }
}
