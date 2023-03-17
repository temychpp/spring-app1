package com.temychp.spring.repositories;

import com.temychp.spring.models.Book;
import com.temychp.spring.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByName(String name);

    List<Book> findByNameStartingWith(String startingWith);

    List<Book> findByOwner(Person owner);

    Optional<Book> findBookByName(String name);

//    @Query
//    ("SELECT Person.\* from Book join Person on Person.id = Book.person_id where Book.id=?")
//    Optional<Person> showBookTaker(int id);
//        return jdbcTemplate.query("SELECT Person.* from Book join Person on Person.id = Book.person_id where Book.id=?",
//                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();

//        return peopleRepository.findById(id);
//    }



}