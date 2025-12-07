package com.example.adapter.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orders")
public class OrderEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String status;
    private Double price;
}