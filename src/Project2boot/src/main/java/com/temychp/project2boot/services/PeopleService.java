package com.temychp.project2boot.services;

//import com.temychp.spring.models.Mood;


import com.temychp.project2boot.models.Book;
import com.temychp.project2boot.models.Person;
import com.temychp.project2boot.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findall() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Person findByName(String name) {
        Optional<Person> foundPerson = peopleRepository.findByName(name);
        return foundPerson.orElse(null);
    }

    public Optional<Person> findPersonByName(String name) {
        return peopleRepository.findPersonByName(name);
    }

    public List<Book> showBooksByPersonId(int id) {
        Optional<Person> personById = peopleRepository.findById(id);
        if (personById.isPresent()) {
            Hibernate.initialize(personById.get().getBooks());
            return personById.get().getBooks();
        } else return Collections.emptyList();
    }

    public void test() {
        System.out.println("inside test");
    }

}