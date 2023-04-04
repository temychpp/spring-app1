package com.temychp.spring.firstRestApp.repositories;

import com.temychp.spring.firstRestApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findPersonByName (String name);

    Optional<Person> findByName (String name);
}