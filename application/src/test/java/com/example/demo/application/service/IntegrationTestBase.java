package com.example.demo.application.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@EnableTransactionManagement(proxyTargetClass = true)
@ContextConfiguration
abstract class IntegrationTestBase {
    @MockitoBean
    PlatformTransactionManager transactionManager;

    // if you need validation
    @Configuration
    static class ValidationConfiguration {
        @Bean
        public LocalValidatorFactoryBean validator() {
            return new LocalValidatorFactoryBean();
        }

        @Bean
        public static MethodValidationPostProcessor validationPostProcessor() {
            MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
            // can handle class (without interface) and align with ValidationAutoConfiguration in spring boot
            processor.setProxyTargetClass(true);
            return processor;
        }
    }
}
