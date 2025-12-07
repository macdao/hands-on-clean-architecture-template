package com.example.adapter.persistence.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class OrderEntityTest {

    @Test
    void shouldCreateOrderEntity() {
        OrderEntity entity = new OrderEntity();
        entity.setId("123");
        entity.setStatus("PENDING");
        entity.setPrice(100.0);

        assertThat(entity.getId()).isEqualTo("123");
        assertThat(entity.getStatus()).isEqualTo("PENDING");
        assertThat(entity.getPrice()).isEqualTo(100.0);
    }
}