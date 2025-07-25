package com.example.demo.adapter.client.inventory;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@RestClientTest
@AutoConfigureMockRestServiceServer(enabled = false)
@AutoConfigureStubRunner(ids = "client:inventory", generateStubs = true)
class InventoryClientTest {

    InventoryClient inventoryClient;

    @StubRunnerPort("inventory")
    int port;

    @BeforeEach
    void setUp(@Autowired RestClient.Builder restClientBuilder) {
        inventoryClient = new InventoryClient(restClientBuilder, "http://localhost:" + port);
    }

    @Test
    void deduct_inventory_should_success() {
        InventoryClient.DeductInventoryRequest request = new InventoryClient.DeductInventoryRequest("product-id-1", 5);
        inventoryClient.deductInventory(request);
    }

    @Test
    void deduct_inventory_should_conflict() {
        InventoryClient.DeductInventoryRequest request = new InventoryClient.DeductInventoryRequest("product-id-2", 5);
        assertThatExceptionOfType(HttpClientErrorException.Conflict.class)
                .isThrownBy(() -> inventoryClient.deductInventory(request));
    }

    @Test
    void deduct_inventory_should_post_failed() {
        InventoryClient.DeductInventoryRequest request =
                new InventoryClient.DeductInventoryRequest("product-id-999", 5);
        assertThatExceptionOfType(HttpServerErrorException.InternalServerError.class)
                .isThrownBy(() -> inventoryClient.deductInventory(request));
    }
}
