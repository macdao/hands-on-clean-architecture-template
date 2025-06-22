package com.example.demo.adapter.client.inventory.adapter;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.example.demo.adapter.client.inventory.InventoryClient;
import com.example.demo.application.port.out.DeductInventoryFailedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ExtendWith(MockitoExtension.class)
class DeductInventorAdapterTest {

    @Mock
    InventoryClient inventoryClient;

    @InjectMocks
    DeductInventorAdapter deductInventorAdapter;

    @Test
    void deductInventory_should_call_success() {
        String productId = "product-id-1";
        int quantity = 5;

        deductInventorAdapter.deductInventory(productId, quantity);

        verify(inventoryClient).deductInventory(new InventoryClient.DeductInventoryRequest(productId, quantity));
    }

    @Test
    void deductInventory_should_throw_DeductInventoryFailedException_when_conflict() {
        String productId = "product-id-2";
        int quantity = 5;
        doThrow(HttpClientErrorException.Conflict.class)
                .when(inventoryClient)
                .deductInventory(any(InventoryClient.DeductInventoryRequest.class));

        assertThatExceptionOfType(DeductInventoryFailedException.class)
                .isThrownBy(() -> deductInventorAdapter.deductInventory(productId, quantity));
    }

    @Test
    void deductInventory_should_propagate_InternalServerError() {
        String productId = "product-id-999";
        int quantity = 5;
        doThrow(HttpServerErrorException.InternalServerError.class)
                .when(inventoryClient)
                .deductInventory(any(InventoryClient.DeductInventoryRequest.class));

        assertThatExceptionOfType(HttpServerErrorException.InternalServerError.class)
                .isThrownBy(() -> deductInventorAdapter.deductInventory(productId, quantity));
    }
}
