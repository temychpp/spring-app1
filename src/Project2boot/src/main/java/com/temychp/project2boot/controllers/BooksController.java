package com.temychp.project2boot.controllers;


import com.temychp.project2boot.models.Book;
import com.temychp.project2boot.models.Person;
import com.temychp.project2boot.services.BookService;
import com.temychp.project2boot.services.PeopleService;
import com.temychp.project2boot.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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
    public String index(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
            @RequestParam(value = "sort_by_year", required = false) boolean sortByYear,
            Model model) {

        if (sortByYear && page != null && booksPerPage != null)
            model.addAttribute("books", bookService.findAllAndPaginationSort(page, booksPerPage));

        else if (sortByYear && page == null && booksPerPage == null)
            model.addAttribute("books", bookService.findAllAndSort());

        else if (!sortByYear && page != null && booksPerPage != null)
            model.addAttribute("books", bookService.findAllAndPagination(page, booksPerPage));

        else
            model.addAttribute("books", bookService.findAll());

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                           @ModelAttribute("person") Person person) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        if (book.getOwner()!= null) {
            model.addAttribute("booktaker", book.getOwner());
        } else {
            model.addAttribute("people", peopleService.findall());
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

    @GetMapping("/search")
    public String searchBookByName(@RequestParam(required = false, defaultValue = "") String startString,
                             Model model) {
        model.addAttribute("startString", startString);
        if (!startString.isEmpty()) {
            model.addAttribute("books", bookService.searchBooks(startString));
        }
        return "books/search";
    }
}