package com.example.demo.adapter.client.inventory;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.demo.application.port.out.DeductInventoryFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@SpringBootTest
@AutoConfigureStubRunner(ids = "client:inventory", generateStubs = true)
class InventoryAdapterTest {

    @Autowired
    RestClient.Builder restClientBuilder;

    private InventoryAdapter inventoryAdapter;

    @StubRunnerPort("inventory")
    int port;

    @BeforeEach
    void setUp() {
        inventoryAdapter = new InventoryAdapter(restClientBuilder, "http://localhost:" + port);
    }

    @Test
    void deduct_inventory_should_success() {
        inventoryAdapter.deductInventory("product-id-1", 5);
    }

    @Test
    void deduct_inventory_should_conflict() {
        assertThatThrownBy(() -> inventoryAdapter.deductInventory("product-id-2", 5))
                .isInstanceOf(DeductInventoryFailedException.class);
    }

    @Test
    void deduct_inventory_should_post_failed() {
        assertThatThrownBy(() -> inventoryAdapter.deductInventory("product-id-999", 5))
                .isInstanceOf(RestClientException.class);
    }
}
