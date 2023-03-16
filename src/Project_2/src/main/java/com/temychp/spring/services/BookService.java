package com.temychp.spring.services;

import com.temychp.spring.models.Book;
import com.temychp.spring.models.Person;
import com.temychp.spring.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Book> findByOwner (Person owner){
        return booksRepository.findByOwner(owner);
    }

    public List<Book> findByName(String bookName){
        return booksRepository.findByName(bookName);
    }

}