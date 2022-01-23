package org.tp.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tp.bookservice.model.Book;

public interface BookStoreRepository extends JpaRepository<Book, Integer> {
}
