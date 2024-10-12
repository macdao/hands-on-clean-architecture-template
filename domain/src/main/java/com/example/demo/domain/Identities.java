package com.example.demo.domain;

import java.util.UUID;

public class Identities {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
