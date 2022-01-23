package org.tp.proxy.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.tp.proxy.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Thomas Pokorny
 * api rest controller proxy to forward requests to the downstream service BookService.
 */
@RestController
public class BookStoreProxyController {

    public static final String BOOK_STORE_URL = System.getenv("BOOKSTORE_SERVICE_HOST") != null ? System.getenv("BOOKSTORE_SERVICE_HOST") : "localhost";
    public static final String BOOK_STORE_PORT = System.getenv("BOOKSTORE_PORT") != null ? System.getenv("BOOKSTORE_PORT") : "8090";
    public static final String BOOK_STORE_API_PATH = "/v1/books";

    private static final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/store/v1/books")
    public List<Book> getBook() {
        Book[] books = restTemplate.getForObject(buildURI(), Book[].class);
        return books != null && books.length != 0 ? new ArrayList<>(Arrays.asList(books)) : new ArrayList<>();
    }

    @PostMapping("/store/v1/books")
    public void createBook(@RequestBody Book book) {
        restTemplate.postForLocation(buildURI(), book);
    }

    @PutMapping("/store/v1/books")
    public void updateBook(@RequestBody Book book) {
        restTemplate.put(buildURI(), book);
    }

    private static String buildURI() {
        String uri = String.format("http://%s:%s%s", BOOK_STORE_URL, BOOK_STORE_PORT, BOOK_STORE_API_PATH);
        return String.format("http://%s:%s%s", BOOK_STORE_URL, BOOK_STORE_PORT, BOOK_STORE_API_PATH);
    }
}
