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
@Transactional
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


    public List<Book> findByName(String bookName){
        return booksRepository.findByName(bookName);
    }

//    public List<Book> findByOwner (Person owner){
//        return booksRepository.findByOwner(owner);
//    }
}