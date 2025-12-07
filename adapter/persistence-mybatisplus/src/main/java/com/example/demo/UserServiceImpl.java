package com.example.adapter.persistence.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.adapter.persistence.entity.UserEntity;
import com.example.adapter.persistence.mapper.UserMapper;
import com.example.adapter.persistence.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}