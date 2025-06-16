package com.example.demo.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.demo.application.port.in.PlaceOrderUseCase.PlaceOrderCommand;
import com.example.demo.application.port.out.DeductInventoryPort;
import com.example.demo.application.port.out.SaveOrderPort;
import jakarta.validation.ConstraintViolationException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest(classes = PlaceOrderService.class)
// enable validation
@EnableAutoConfiguration
class PlaceOrderServiceIntegrationTest {

    @MockitoBean
    SaveOrderPort saveOrderPort;

    @MockitoBean
    DeductInventoryPort deductInventoryPort;

    @MockitoBean
    TransactionTemplate transactionTemplate;

    @Autowired
    PlaceOrderService placeOrderService;

    @Test
    void place_order_should_create_and_save_order() {
        PlaceOrderCommand command = new PlaceOrderCommand("user-id-1", "product-id-1", 1, new BigDecimal("100.0"));

        placeOrderService.placeOrder(command);
    }

    @Test
    void place_order_should_throw_exception_when_price_is_null() {
        PlaceOrderCommand command = new PlaceOrderCommand("user-id-1", "product-id-1", 1, null);

        assertThatThrownBy(() -> placeOrderService.placeOrder(command))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
