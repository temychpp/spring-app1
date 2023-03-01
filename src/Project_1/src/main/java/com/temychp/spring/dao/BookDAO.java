package com.temychp.spring.dao;

import com.temychp.spring.models.Book;
import com.temychp.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));

    }

    public Optional<Book> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }


    public void save(Book book) {

        jdbcTemplate.update("INSERT INTO Book(name, author, year_of_production) VALUES (?,?,?)",
                book.getName(), book.getAuthor(), book.getYearOfProduction());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_production=? WHERE id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYearOfProduction(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }

    public Optional<Book> showBookByName(String bookName) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?", new Object[]{bookName},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

}










