package com.assigment.books.person.domain;

import com.assigment.books.person.domain.models.Person;
import com.assigment.books.person.application.response.PersonDTO;

public class utils {
    public static PersonDTO createDTOFromPerson(Person person) {
        return new PersonDTO(person);
    }
}
