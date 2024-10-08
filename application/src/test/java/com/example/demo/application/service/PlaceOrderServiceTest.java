package com.example.demo.application.service;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import com.example.demo.application.port.in.PlaceOrderUseCase.PlaceOrderCommand;
import com.example.demo.application.port.out.SaveOrderPort;
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

    @Test
    void place_order_should_create_and_save_order() {
        PlaceOrderCommand command = new PlaceOrderCommand("user-id", new BigDecimal("100.0"));

        placeOrderService.placeOrder(command);

        verify(transactionTemplate).execute(transactionCallbackCaptor.capture());

        transactionCallbackCaptor.getValue().doInTransaction(null);
        verify(saveOrderPort)
                .save(argThat(order -> order.getBuyerId().value().equals("user-id")
                        && order.getPrice().compareTo(new BigDecimal("100.0")) == 0));
    }
}
