package com.example.demo.application.service;

import com.example.demo.application.port.out.DeductInventoryPort;
import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.application.port.out.SaveOrderPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
abstract class IntegrationTestBase {
    @MockitoBean
    PlatformTransactionManager transactionManager;

    @MockitoBean
    FindOrderPort findOrderPort;

    @MockitoBean
    SaveOrderPort saveOrderPort;

    @MockitoBean
    TransactionTemplate transactionTemplate;

    @MockitoBean
    DeductInventoryPort deductInventoryPort;
}
