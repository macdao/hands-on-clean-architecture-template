package com.example.demo.adapter.persistencejdbc.person;

import com.example.demo.application.port.out.SavePersonPort;
import com.example.demo.domain.person.Person;
import org.springframework.data.repository.ListCrudRepository;

public interface PersonRepository extends ListCrudRepository<Person, Person.PersonId>, SavePersonPort {}
