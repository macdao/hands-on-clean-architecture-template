package com.example.demo.adapter.client.inventory;

import org.springframework.web.client.RestClient;

public class InventoryClient {
    private final RestClient restClient;

    public InventoryClient(RestClient.Builder restClientBuilder, String baseUrl) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
    }

    public void deductInventory(DeductInventoryRequest deductInventoryRequest) {
        restClient
                .post()
                .uri("/deduct-inventory")
                .body(deductInventoryRequest)
                .retrieve()
                .toBodilessEntity();
    }

    public record DeductInventoryRequest(String productId, int quantity) {}
}
