package com.example.demo.application.port.out;

public interface DeductInventoryPort {
    void deductInventory(String productId, int quantity) throws DeductInventoryFailedException;
}
