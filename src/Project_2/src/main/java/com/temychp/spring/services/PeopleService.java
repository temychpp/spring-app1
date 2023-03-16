package com.temychp.spring.services;

//import com.temychp.spring.models.Mood;
import com.temychp.spring.models.Book;
import com.temychp.spring.models.Person;
import com.temychp.spring.repositories.BooksRepository;
import com.temychp.spring.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository,
                         BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findALL() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Person findByName(String name) {
        Optional<Person> foundPerson= peopleRepository.findByName(name);
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


    public Person findPersonByName(String name) {
        Optional<Person> person = peopleRepository.findPersonByName(name);
        return person.orElse(null);
    }

    public List<Book> showBooksByPersonId(Person owner) {
      return booksRepository.findByOwner(owner);

    }

}