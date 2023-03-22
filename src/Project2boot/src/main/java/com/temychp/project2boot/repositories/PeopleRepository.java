package com.temychp.project2boot.repositories;

import com.temychp.project2boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findPersonByName (String name);

    Optional<Person> findByName (String name);
}