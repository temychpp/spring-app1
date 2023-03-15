package com.temychp.spring.repositories;

import com.temychp.spring.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByName(String name);

    List<Book> findByNameStartingWith(String startingWith);

}