package com.example.demo.application.service;

import com.example.demo.application.port.out.FindOrderPort;
import com.example.demo.application.port.out.SaveOrderPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
abstract class IntegrationTestBase {
    @MockBean
    PlatformTransactionManager transactionManager;

    @MockBean
    FindOrderPort findOrderPort;

    @MockBean
    SaveOrderPort saveOrderPort;

    @MockBean
    TransactionTemplate transactionTemplate;
}
