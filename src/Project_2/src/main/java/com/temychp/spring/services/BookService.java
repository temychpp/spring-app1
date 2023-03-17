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
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfProduction") ) ).getContent();
    }


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

    public List<Book> findByNameStartingWith(String startingWith){
        return booksRepository.findByNameStartingWith(startingWith);
    };

    @Transactional
    public List<Book> findByOwner (Person owner){
        return booksRepository.findByOwner(owner);
    }

    public List<Book> findByName(String bookName){
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
      //  booksRepository.save(book);
//        personWhichTakeBook.
//        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?",
//                personWhichTakeBook.getId(), id);
    }
    @Transactional
    public void giveBookToLibrary(int id) {
       Book book = booksRepository.findById(id).orElse(null);
        assert book != null;
        book.setOwner(null);
      //  booksRepository.save(book);
//        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?",id);
    }

    @Transactional
    public Optional<Person> showBookTaker(int id) {
//        return jdbcTemplate.query("SELECT Person.* from Book join Person on Person.id = Book.person_id where Book.id=?",
//                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
        Book book = booksRepository.findById(id).orElse(null);

        assert book != null;
        Person person =  book.getOwner();

        int personId   =  person.getId();
        return peopleRepository.findById(personId);
    }
}
