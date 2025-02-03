package com.example.demo.adapter.web.order.adapter;

import static org.mockito.Mockito.verify;

import com.example.demo.application.port.in.PayOrderUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PayOrderAdapterTest {
    @Mock
    private PayOrderUseCase payOrderUseCase;

    @InjectMocks
    private PayOrderAdapter payOrderAdapter;

    @Test
    void should_pay_success() {
        String orderId = "order-id-1";

        payOrderAdapter.payOrder(orderId);

        verify(payOrderUseCase).payOrder(orderId);
    }
}
