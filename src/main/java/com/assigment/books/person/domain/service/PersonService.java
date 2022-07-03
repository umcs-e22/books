package com.assigment.books.person.domain.service;
import com.assigment.books.person.domain.models.Person;
import com.assigment.books.person.application.response.PersonDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface PersonService {

    private Person createPersonFromDTO(PersonDTO personDTO) {
        return null;
    }

    CollectionModel<EntityModel<Person>> all();

    ResponseEntity<?> getByEmail(String email);

    ResponseEntity<?> getByUUID(String email);

//    ResponseEntity<?> removeOne(String email);
//
//    ResponseEntity<?> updateOrCreate(Person person);
//
//    ResponseEntity<?> updateOrCreate(PersonDTO personDTO);
}
