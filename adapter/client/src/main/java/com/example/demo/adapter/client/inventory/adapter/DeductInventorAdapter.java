package com.example.demo.adapter.client.inventory.adapter;

import com.example.demo.adapter.client.inventory.InventoryClient;
import com.example.demo.application.port.out.DeductInventoryFailedException;
import com.example.demo.application.port.out.DeductInventoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
public class DeductInventorAdapter implements DeductInventoryPort {
    private final InventoryClient inventoryClient;

    @Override
    public void deductInventory(String productId, int quantity) throws DeductInventoryFailedException {
        InventoryClient.DeductInventoryRequest deductInventoryRequest =
                new InventoryClient.DeductInventoryRequest(productId, quantity);
        try {
            inventoryClient.deductInventory(deductInventoryRequest);
        } catch (HttpClientErrorException.Conflict conflict) {
            throw new DeductInventoryFailedException();
        }
    }
}
