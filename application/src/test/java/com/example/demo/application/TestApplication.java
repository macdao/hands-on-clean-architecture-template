package com.example.demo.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(proxyBeanMethods = false)
@EnableTransactionManagement(proxyTargetClass = true)
class TestApplication {}
