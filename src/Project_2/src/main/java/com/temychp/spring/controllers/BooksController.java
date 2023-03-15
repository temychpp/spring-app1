package com.temychp.spring.controllers;

import com.temychp.spring.dao.BookDAO;
import com.temychp.spring.dao.PersonDAO;
import com.temychp.spring.models.Book;
import com.temychp.spring.models.Person;
import com.temychp.spring.services.BookService;
import com.temychp.spring.services.PeopleService;
import com.temychp.spring.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {


    private final PeopleService peopleService;

    private final BookService bookService;

    private final BookDAO bookDAO;
    private final BookValidator bookValidator;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookService bookService, BookValidator bookValidator, PeopleService peopleService, BookDAO bookDAO, PersonDAO personDAO) {
        this.peopleService = peopleService;
        this.bookService = bookService;
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));

        model.addAttribute("person", bookDAO.showBookTaker(id));

        Optional<Person> bookTaker = bookDAO.showBookTaker(id);

        if (bookTaker.isPresent()) {
            model.addAttribute("booktaker", bookTaker.get());
        } else {
            model.addAttribute("people", peopleService.findALL());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/giveBookToLibrary")
    public String giveBookToLibrary(@PathVariable("id") int id) {
        bookDAO.giveBookToLibrary(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/giveBookToPerson")
    public String giveBookToPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.giveBookToPerson(id, person);
        return "redirect:/books/{id}";
    }
}