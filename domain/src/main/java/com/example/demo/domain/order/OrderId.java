package com.example.demo.domain.order;

import com.example.demo.domain.Identities;

public record OrderId(String value) {
    public OrderId() {
        this(Identities.generateId());
    }
}
