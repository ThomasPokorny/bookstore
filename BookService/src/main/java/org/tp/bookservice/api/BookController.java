package org.tp.bookservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tp.bookservice.model.Book;
import org.tp.bookservice.repository.BookStoreRepository;

import java.util.List;

/**
 * @author Thomas Pokorny
 * rest controller for the bookservice,
 * offers POST GET and PUT methods on '/v1/books'.
 */
@RestController
public class BookController {

    @Autowired
    private BookStoreRepository repository;

    @GetMapping("/v1/books")
    public List<Book> getBook() {
        return repository.findAll();
    }

    @PostMapping("/v1/books")
    public void createBook(@RequestBody Book book) {
        repository.save(book);
    }

    @PutMapping("/v1/books")
    public void updateBook(@RequestBody Book book) {
        repository.save(book);
    }
}
