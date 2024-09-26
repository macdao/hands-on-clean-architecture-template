package com.example.demo.adapter.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, String> {}
