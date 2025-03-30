package com.example.demo.adapter.persistencejdbc.person;

import com.example.demo.application.port.out.SavePersonPort;
import com.example.demo.domain.person.Address;
import com.example.demo.domain.person.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PersonRepositoryTest {
    @Autowired
    SavePersonPort sut;

    @Autowired
    ListCrudRepository<Person, Person.PersonId> personRepository;

    @Test
    void save_should_save_person() {
        var person = new Person("John", "Smith", new Address("State", "Country"));

        sut.save(person);

        var savedPerson = personRepository.findById(person.getId()).orElseThrow();
        assertThat(savedPerson)
                .returns(person.getId(), from(Person::getId))
                .returns("John", from(Person::getFirstname))
                .returns("Smith", from(Person::getLastname))
                .returns(0, from(Person::getVersion));

        assertThat(savedPerson.getCreatedDate()).isCloseTo(Instant.now(), within(1, ChronoUnit.SECONDS));
        assertThat(savedPerson.getLastModifiedDate()).isCloseTo(Instant.now(), within(1, ChronoUnit.SECONDS));
    }
}
