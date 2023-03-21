package com.temychp.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 30, message = "name should be between 1 and 30 characters")
    @Pattern(regexp = "[А-Яа-яё\\s]+", message = "incorrect name")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Pattern(regexp = "[А-Я][а-я]+\\s+[А-Я][а-я]+", message = "incorrect name")
    @Column(name = "author")
    private String author;

    @Min(value = 868, message = "Year of production should be greater than 868")
    @Column(name = "year_of_production")
    private int yearOfProduction;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "date_of_rent")
    private LocalDateTime dateOfRent;

    @Transient
    private boolean delay;

    public Book() {
    }

    public Book(int id, String name, String author, int yearOfProduction) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfProduction = yearOfProduction;
    }

    public boolean resultDelay() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateOfRent, now);
        if (duration.toDays() > 10)
            setDelay(true);
        return delay;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public LocalDateTime getDateOfRent() {
        return dateOfRent;
    }

    public void setDateOfRent(LocalDateTime dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    public boolean getDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}