package com.temychp.spring.controllers;

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

    private final BookService bookService;

    private final BookValidator bookValidator;

    private final PeopleService peopleService;


    @Autowired
    public BooksController(BookService bookService, BookValidator bookValidator, PeopleService peopleService) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());

//            bookService.findByNameStartingWith();
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));

        model.addAttribute("person", bookService.showBookTaker(id));

        Optional<Person> bookTaker = bookService.showBookTaker(id);

        if (bookTaker.isPresent()) {
            model.addAttribute("booktaker", bookTaker.get());
        } else {
            model.addAttribute("people", peopleService.findALL());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

//
    @PatchMapping("/{id}/giveBookToLibrary")
    public String giveBookToLibrary(@PathVariable("id") int id) {
        bookService.giveBookToLibrary(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/giveBookToPerson")
    public String giveBookToPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookService.giveBookToPerson(id, person);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/search")
    public String searchBookByName(@ModelAttribute("book") Book book) {
        bookService.findByNameStartingWith(book.getName());
        return "redirect:/books/search";
    }


}