package com.example.adapter.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.adapter.persistence.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
}