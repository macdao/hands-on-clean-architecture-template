package com.example.demo.adapter.web;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.application.port.in.PayOrderUseCase;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
abstract class ContractTestBase {

    @Autowired
    WebApplicationContext context;

    @MockBean
    PayOrderUseCase payOrderUseCase;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }
}