package com.example.demo.domain.person;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PersonTest {
    @Test
    void new_person_should_be_active() {
        Person person = new Person("firstname", "lastname", new Address("city", "country"));
        assertThat(person.getActiveStatus()).isEqualTo(Person.PersonStatus.ACTIVE);
    }
}
