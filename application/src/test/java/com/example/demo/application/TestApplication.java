package com.example.demo.application;

import static org.mockito.Mockito.mock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
class TestApplication {
    @Bean
    PlatformTransactionManager platformTransactionManager() {
        return mock(PlatformTransactionManager.class);
    }
}
