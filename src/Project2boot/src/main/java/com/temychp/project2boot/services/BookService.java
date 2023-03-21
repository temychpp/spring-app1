package com.temychp.project2boot.services;

import com.temychp.project2boot.models.Book;
import com.temychp.project2boot.models.Person;
import com.temychp.project2boot.repositories.BooksRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
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

    public Optional<Book> findBookByName(String name) {
        return booksRepository.findBookByName(name);
    }

    @Transactional
    public void giveBookToPerson(int id, Person personWhichTakeBook) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
//              Hibernate.initialize(book.get());
            book.get().setOwner(personWhichTakeBook);
            book.get().setDateOfRent(LocalDateTime.now());
            booksRepository.save(book.get());
        }
    }

    @Transactional
    public void giveBookToLibrary(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
//            Hibernate.initialize(book.get());
            book.get().setOwner(null);
            book.get().setDateOfRent(null);
            booksRepository.save(book.get());
        }
    }

    public List<Book> searchBooks(String startString) {
        return booksRepository.findByNameStartingWithIgnoreCase(startString);
    }
}