package com.assigment.books.person.application.response;

import com.assigment.books.person.domain.models.Gender;
import com.assigment.books.person.domain.models.Person;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {

    private String firstName;
    private String secondName;
    private String email;

    @PersistenceConstructor
    @Builder
    public PersonDTO(String firstName, String secondName, String email, Gender gender, String clubName, String coachEmail) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public PersonDTO(Person person) {
        this.firstName = person.getFirstName();
        this.secondName = person.getSecondName();
        this.email = person.getEmail();
    }
}
