package com.temychp.spring.services;

import com.temychp.spring.models.Book;
import com.temychp.spring.models.Person;
import com.temychp.spring.repositories.BooksRepository;
import com.temychp.spring.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;


    @Autowired
    public BookService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAllAndPagination(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> findAllAndSort() {
        return booksRepository.findAll(Sort.by("yearOfProduction"));
    }

    public List<Book> findAllAndPaginationSort(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfProduction"))).getContent();
    }

//    public boolean getResultDelay(){
//        return booksRepository.fResultDelay();
//    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public List<Book> findByNameStartingWith(String startingWith) {
        return booksRepository.findByNameStartingWith(startingWith);
    }

    @Transactional
    public List<Book> findByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
    }

    public List<Book> findByName(String bookName) {
        return booksRepository.findByName(bookName);
    }

    public Optional<Book> findBookByName(String name) {
        return booksRepository.findBookByName(name);
    }

    @Transactional
    public void giveBookToPerson(int id, Person personWhichTakeBook) {
        Book book = booksRepository.findById(id).orElse(null);
        assert book != null;
        book.setOwner(personWhichTakeBook);
        book.setDateOfRent(LocalDateTime.now());
        booksRepository.save(book);
    }

    @Transactional
    public void giveBookToLibrary(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        assert book != null;
        book.setOwner(null);
        book.setDateOfRent(null);
        booksRepository.save(book);
    }

    @Transactional
    public Optional<Person> showBookTaker(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        assert book != null;

        Optional<Person> person = Optional.ofNullable(book.getOwner());
        if (person.isPresent()) {
            int personId = person.get().getId();
            return peopleRepository.findById(personId);
        } else return peopleRepository.findById(id);
    }
}