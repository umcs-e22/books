package com.assigment.books.domain.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String author;
    private String title;

    @Indexed(unique = true)
    private String bookUUID;

    public Book(String author, String title, String bookUUID) {
        this.author = author;
        this.title = title;
        this.bookUUID = bookUUID;
    }
}
