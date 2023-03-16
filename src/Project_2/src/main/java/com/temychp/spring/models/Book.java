package com.temychp.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 30, message = "name should be between 1 and 30 characters")
    @Pattern(regexp = "[А-Яа-я\\s]+",message = "incorrect name")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Pattern(regexp = "[А-Я][а-я]+\\s+[А-Я][а-я]+",message = "incorrect name")
    @Column(name = "author")
    private String author;

    @Min(value = 868, message = "Year of production should be greater than 868")
    @Column(name = "year_of_production")
    private int yearOfProduction;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book() {
    }

    public Book(int id, String name, String author, int yearOfProduction) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfProduction = yearOfProduction;
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