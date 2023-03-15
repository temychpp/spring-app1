package com.temychp.spring.services;

//import com.temychp.spring.models.Mood;
import com.temychp.spring.models.Person;
import com.temychp.spring.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    public List<Person> findALL() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<Person> findByName(String name) {
        List<Person> foundPerson= peopleRepository.findByName(name);
        return foundPerson;
    }

    @Transactional
    public void save(Person person) {
//        person.setCreatedAt(new Date());
//        person.setMood(Mood.CALM);
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


    public Optional<People> findPersonByName(String name) {
        People person = peopleRepository.findPersonByName(name);
        return person;
    }
}