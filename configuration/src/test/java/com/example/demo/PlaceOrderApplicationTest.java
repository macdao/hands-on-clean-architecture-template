package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.adapter.client.inventory.InventoryAdapter;
import com.example.demo.adapter.persistence.order.OrderEntity;
import com.example.demo.adapter.persistence.order.OrderEntityRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClient;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = "client:inventory", generateStubs = true)
class PlaceOrderApplicationTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @AfterEach
    void tearDown() {
        orderEntityRepository.deleteAll();
    }

    @Test
    void place_order_should_success() throws Exception {
        mvc.perform(post("/api/orders")
                        .header("Authorization", "my-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\": \"product-id-1\", \"quantity\": 1, \"price\": 100}"))
                .andExpect(status().isCreated());

        List<OrderEntity> all = orderEntityRepository.findAll();
        assertThat(all).hasSize(1);
        var savedOrder = all.getFirst();
        assertThat(savedOrder.getProductId()).isEqualTo("product-id-1");
        assertThat(savedOrder.getQuantity()).isEqualTo(1);
        assertThat(savedOrder.getPrice()).isEqualByComparingTo("100");
    }

    @TestConfiguration
    static class Config {

        @StubRunnerPort("inventory")
        int port;

        @Bean
        @Primary
        InventoryAdapter testInventoryAdapter(RestClient.Builder restClientBuilder) {
            return new InventoryAdapter(restClientBuilder, "http://localhost:" + port);
        }
    }
}
