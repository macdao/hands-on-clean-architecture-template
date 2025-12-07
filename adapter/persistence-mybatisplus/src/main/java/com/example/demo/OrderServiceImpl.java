package com.example.adapter.persistence.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.adapter.persistence.entity.OrderEntity;
import com.example.adapter.persistence.mapper.OrderMapper;
import com.example.adapter.persistence.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {
}