package com.example.adapter.persistence.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.adapter.persistence.mapper")
public class MyBatisPlusConfig {
    // Basic configuration - pagination can be added later if needed
}