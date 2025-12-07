package com.example.adapter.persistence.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserEntityTest {

    @Test
    void shouldCreateUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setId("456");
        entity.setName("John Doe");

        assertThat(entity.getId()).isEqualTo("456");
        assertThat(entity.getName()).isEqualTo("John Doe");
    }
}