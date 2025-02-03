package com.example.demo.adapter.web.order.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;

import com.example.demo.adapter.web.order.PlaceOrderController;
import com.example.demo.application.port.in.PlaceOrderUseCase;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

@ExtendWith(MockitoExtension.class)
class PlaceOrderAdapterTest {
    @Mock
    private PlaceOrderUseCase placeOrderUseCase;

    @InjectMocks
    private PlaceOrderAdapter placeOrderAdapter;

    @Test
    void place_order_should_call_use_case() {
        placeOrderAdapter.placeOrder(
                new PlaceOrderController.PlaceOrderRequest("product-id-1", 10, new BigDecimal("200.0")),
                User.withUsername("user1").password("").build());

        verify(placeOrderUseCase).placeOrder(assertArg(command -> {
            assertThat(command)
                    .returns("user1", from(PlaceOrderUseCase.PlaceOrderCommand::buyerId))
                    .returns("product-id-1", from(PlaceOrderUseCase.PlaceOrderCommand::productId))
                    .returns(10, from(PlaceOrderUseCase.PlaceOrderCommand::quantity))
                    .returns(new BigDecimal("200.0"), from(PlaceOrderUseCase.PlaceOrderCommand::price));
        }));
    }
}
