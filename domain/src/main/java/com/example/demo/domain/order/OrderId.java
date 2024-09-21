package com.example.demo.domain.order;

import java.util.UUID;

public record OrderId(String value) {
    public OrderId() {
        this(UUID.randomUUID().toString());
    }
}