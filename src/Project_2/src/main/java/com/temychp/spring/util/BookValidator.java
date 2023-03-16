package com.temychp.spring.util;

//import com.temychp.spring.dao.BookDAO;
import com.temychp.spring.models.Book;
import com.temychp.spring.services.BookService;
import com.temychp.spring.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BookService bookService;

    @Autowired
    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
//        if (bookDAO.showBookByName(book.getName()).isPresent())
//            errors.rejectValue("name", "", "This book name is already taken booknamenotvalid");
    }
}