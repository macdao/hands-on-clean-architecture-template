package com.example.demo.adapter.persistencejdbc;

import com.example.demo.domain.person.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
class JdbcConfiguration extends AbstractJdbcConfiguration {
    @Override
    protected List<?> userConverters() {
        return Arrays.asList(new PersonIdToStringConverter(), new StringToPersonIdConverter());
    }

    private static class StringToPersonIdConverter implements Converter<String, Person.PersonId> {
        @Override
        public Person.PersonId convert(String source) {
            return new Person.PersonId(source);
        }
    }

    private static class PersonIdToStringConverter implements Converter<Person.PersonId, String> {
        @Override
        public String convert(Person.PersonId source) {
            return source.value();
        }
    }
}
