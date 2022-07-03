package com.assigment.books.domain.repository;

import com.assigment.books.domain.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BooksRepository extends MongoRepository<Book, String> {
    Optional<Book> findByBookUUID(String uuid);
}