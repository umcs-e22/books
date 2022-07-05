package com.assigment.books.domain.service;

import com.assigment.books.application.response.BookDTO;
import com.assigment.exceptions.NotFoundException;
import com.assigment.books.domain.models.Book;
import com.assigment.books.domain.repository.BooksRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class BooksService {

    protected final BooksRepository booksRepository;

    public List<Book> all() {
        log.info("Returning all books");
        return booksRepository.findAll();
    }

    public ResponseEntity<?> getByUUID(String uuid) {
        log.info("Getting person:<"+uuid+">");
        return booksRepository.findByBookUUID(uuid)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Book", uuid));

    }

    public ResponseEntity<?> removeOne(String uuid){
        booksRepository.findByBookUUID(uuid)
                .ifPresentOrElse(p-> {
                    log.info("Deleted: "+ uuid + " from books repository");
                    booksRepository.delete(p)
                    ;}, ()->{log.info("Not deleted: "+uuid+" because do not exist already.");});

        return ResponseEntity.ok("");
    }

    public ResponseEntity<?> createBook(BookDTO bookDTO) {
        log.info("Creating new book: "+ bookDTO.getTitle());
        Book book = new Book(bookDTO.getAuthor(), bookDTO.getTitle(), UUID.randomUUID().toString(), bookDTO.getPrice());
        Book newBook = booksRepository.save(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
}
