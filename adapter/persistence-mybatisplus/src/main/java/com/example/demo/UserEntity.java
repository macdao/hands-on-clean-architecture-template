package com.example.adapter.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class UserEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
}