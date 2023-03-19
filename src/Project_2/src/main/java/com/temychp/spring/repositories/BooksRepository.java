package com.temychp.spring.repositories;

import com.temychp.spring.models.Book;
import com.temychp.spring.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByName(String name);

    List<Book> findByNameStartingWith(String startingWith);

    List<Book> findByOwner(Person owner);

    Optional<Book> findBookByName(String name);

}