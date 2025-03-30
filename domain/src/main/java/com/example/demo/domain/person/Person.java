package com.example.demo.domain.person;

import com.example.demo.domain.Identities;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.Instant;

import static org.springframework.data.relational.core.mapping.Embedded.OnEmpty.USE_NULL;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = @PersistenceCreator)
public class Person {
    @Id
    private final PersonId id;

    private String firstname;
    private String lastname;
    private PersonStatus activeStatus;

    @Embedded(onEmpty = USE_NULL)
    private Address address;

    private final Instant createdDate;
    private Instant lastModifiedDate;

    @Version
    Integer version;

    public Person(String firstname, String lastname, Address address) {
        this.id = new PersonId();
        this.firstname = firstname;
        this.lastname = lastname;
        this.activeStatus = PersonStatus.ACTIVE;
        this.address = address;
        this.createdDate = Instant.now();
        this.lastModifiedDate = Instant.now();
    }

    public record PersonId(String value) {
        public PersonId() {
            this(Identities.generateId());
        }
    }

    public enum PersonStatus {
        ACTIVE,
        INACTIVE
    }
}
