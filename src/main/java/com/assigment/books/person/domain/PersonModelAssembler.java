package com.assigment.books.person.domain;


import com.assigment.books.person.domain.models.Person;
import com.assigment.books.person.application.controller.PersonController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This class is used for generating links each Person object.
 */
@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

    @Override
    public EntityModel<Person> toModel(Person person){
        return EntityModel.of(person,
                WebMvcLinkBuilder.linkTo(methodOn(PersonController.class).one(person.getEmail())).withSelfRel(),
                linkTo(methodOn(PersonController.class).all()).withRel("persons"));
    }

    @Override
    public CollectionModel<EntityModel<Person>> toCollectionModel(Iterable<? extends Person> entities) {
        var persons = StreamSupport.stream(entities.spliterator(), false).map(this::toModel).toList();
        return CollectionModel.of(persons,
                linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }
}
