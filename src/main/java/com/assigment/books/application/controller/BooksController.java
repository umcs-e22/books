package com.assigment.books.application.controller;

import com.assigment.books.application.response.BookDTO;
import com.assigment.books.domain.models.Book;
import com.assigment.books.domain.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/books")
@AllArgsConstructor
public class BooksController {

    private final BooksService booksService;

    @GetMapping()
    public List<Book> all(){
        return booksService.all();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> one(@PathVariable String uuid){
        return booksService.getByUUID(uuid);
    }

    @PostMapping("")
    public ResponseEntity<?> addOne(@RequestHeader("X-auth-user-roles") String userRoles, @RequestBody BookDTO book) {
        if(userRoles.contains("ROLE_BASdIC_USER")){
            return booksService.createBook(book);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> removeOne(@PathVariable String uuid){
        return booksService.removeOne(uuid);
    }
}
