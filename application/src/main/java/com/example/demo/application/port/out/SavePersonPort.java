package com.example.demo.application.port.out;

import com.example.demo.domain.person.Person;

public interface SavePersonPort {
    Person save(Person person);
}
