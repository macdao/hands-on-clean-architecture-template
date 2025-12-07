package com.example.adapter.persistence.repository;

import com.example.adapter.persistence.entity.UserEntity;
import com.example.adapter.persistence.mapper.UserMapper;
import com.example.adapter.persistence.service.UserService;
import com.example.demo.application.port.out.SavePersonPort;
import com.example.demo.domain.person.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonRepositoryAdapter implements SavePersonPort {

    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public Person save(Person person) {
        UserEntity entity = new UserEntity();
        entity.setId(person.getId().value());
        entity.setName(person.getFirstname() + " " + person.getLastname());
        userService.save(entity);
        return person; // Return the same person for simplicity
    }
}