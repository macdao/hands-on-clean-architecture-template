package com.example.demo.adapter.web;

import com.example.demo.adapter.web.order.PayOrderController;
import com.example.demo.adapter.web.order.PlaceOrderController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
abstract class ContractTestBase {

    @Autowired
    WebApplicationContext context;

    @MockitoBean
    PayOrderController.PayOrderHandler payOrderHandler;

    @MockitoBean
    PlaceOrderController.PlaceOrderHandler placeOrderHandler;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }
}
