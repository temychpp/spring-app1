package com.temychp.spring.firstRestApp.services;

import com.temychp.spring.firstRestApp.models.Person;
import com.temychp.spring.firstRestApp.repositories.PeopleRepository;
import com.temychp.spring.firstRestApp.util.PersonErrorResponse;
import com.temychp.spring.firstRestApp.util.PersonNotCreatedException;
import com.temychp.spring.firstRestApp.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response =
                new PersonErrorResponse("Person with this id not found",
                        System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        PersonErrorResponse response =
                new PersonErrorResponse(
                        e.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}