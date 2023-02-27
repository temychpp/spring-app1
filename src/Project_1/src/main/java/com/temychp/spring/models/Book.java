package com.temychp.spring.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


public class Book {

    private int id;
    @NotEmpty(message = "name should not be empty")
  //  @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "incorrect name")
    @Size(min = 10, max = 30, message = "name should be between 10 and 30 characters")
    private String name;

    @NotEmpty(message = "author should not be empty")
    private String author;

    @Min(value = 868, message = "Age should be greater than 868")
    private int yearOfProduction;

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

}
