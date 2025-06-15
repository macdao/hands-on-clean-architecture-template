package com.example.demo;

import static org.springframework.boot.test.context.SpringBootTest.UseMainMethod.ALWAYS;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// test the main method
// https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications.using-main
@SpringBootTest(useMainMethod = ALWAYS)
class DemoApplicationTests {

    @Test
    void contextLoads() {}
}
