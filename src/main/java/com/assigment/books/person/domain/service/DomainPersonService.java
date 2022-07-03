package com.assigment.books.person.domain.service;

import com.assigment.books.exceptions.NotFoundException;
import com.assigment.books.person.domain.models.Person;
import com.assigment.books.person.domain.repository.PersonRepository;
import com.assigment.books.person.application.response.PersonDTO;
import com.assigment.books.person.domain.PersonModelAssembler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DomainPersonService implements PersonService {

    protected final PersonRepository personRepository;
    protected final PersonModelAssembler personAssembler;

    private Person createPersonFromDTO(PersonDTO personDTO){
        return new Person(personDTO.getFirstName(), personDTO.getSecondName(), personDTO.getEmail());

    }
    public CollectionModel<EntityModel<Person>> all() {
        log.info("Returning all persons");
        return personAssembler.toCollectionModel(personRepository.findAll());
    }

    public ResponseEntity<?> getByEmail(String email) {
        log.info("Getting person:<"+email+">");
        return personRepository.findByEmail(email)
                .map(personAssembler::toModel).map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Person", email));

    }

    public ResponseEntity<?> getByUUID(String uuid) {
        log.info("Getting person:<"+uuid+">");
        return personRepository.findByUserUUID(uuid)
                .map(personAssembler::toModel).map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Person", uuid));

    }

//    public ResponseEntity<?> removeOne(String uuid){
//        if(!AuthenticationFacade.isAdmin()){
//            return new ResponseEntity<>("You are not allowed to modify this content", HttpStatus.FORBIDDEN);
//        }
//        personRepository.findByUserUUID(uuid)
//                .ifPresentOrElse(p-> {
//                    log.info("Deleted: "+ uuid + " from person repository");
//                    personRepository.delete(p)
//                    ;}, ()->{log.info("Not deleted: "+uuid+" because do not exist already.");});
//
//        userRepository.findByUserUUID(uuid)
//                .ifPresentOrElse(u-> {
//                    log.info("Deleted: "+ uuid + " from user repository");
//                    userRepository.delete(u)
//                    ;}, ()->{log.info("Not deleted: "+uuid+" because do not exist already.");});
//
//        return ResponseEntity.ok("");
//    }

//    public ResponseEntity<?> updateOrCreate(Person person){
//        return updateOrCreate(new PersonDTO(person));
//    }
//    public ResponseEntity<?> updateOrCreate(PersonDTO personDTO) {
//        if(!AuthenticationFacade.isAdmin() && !AuthenticationFacade.isModifingOwnData(personDTO.getEmail())) {
//            return new ResponseEntity<>("You are not allowed to modify thihs content", HttpStatus.FORBIDDEN);
//        }
//        //if person is found then return response with 203 status (See other)
//        //else create new person.
//        return personRepository.findByEmail(personDTO.getEmail()).
//                map(p -> {
//                    log.info("Updating: ."+personDTO.getEmail() +" Person already exist");
//                    Person person = createPersonFromDTO(personDTO);
//                    p.update(person);
//                    personRepository.save(p);
//                    return new ResponseEntity<>(personAssembler.toModel(p), HttpStatus.SEE_OTHER);
//                })
//                .orElseGet(() ->
//                {
//                    log.info("Creating new person: "+ personDTO.getEmail());
//                    Person fromDTO = createPersonFromDTO(personDTO);
//                    Person newPerson = personRepository.save(new Person(fromDTO));
//                    EntityModel<Person> entityModel = personAssembler.toModel(newPerson);
//                    return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
//                });
//    }
}
