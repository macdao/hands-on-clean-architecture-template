package com.example.demo.config;

import com.example.demo.adapter.client.inventory.InventoryClient;
import com.example.demo.adapter.client.inventory.adapter.DeductInventorAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class InventoryConfig {
    @Bean
    InventoryClient inventoryAdapter(
            RestClient.Builder restClientBuilder, @Value("${example.inventory.base-url}") String baseUrl) {
        return new InventoryClient(restClientBuilder, baseUrl);
    }

    @Bean
    DeductInventorAdapter deductInventorAdapter(InventoryClient inventoryAdapter) {
        return new DeductInventorAdapter(inventoryAdapter);
    }
}
