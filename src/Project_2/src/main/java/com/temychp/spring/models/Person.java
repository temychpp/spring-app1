package com.temychp.spring.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {

    private int id;
    @NotEmpty(message = "name should not be empty")
    @Pattern(regexp = "[А-Я][а-я]+\\s+[А-Я][а-я]+\\s[А-Я][а-я]+",message = "incorrect name")
    @Size(min = 10, max = 30, message = "name should be between 10 and 30 characters")
    private String name;
    @Min(value = 1900, message = "Age should be greater than 1900")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int id, String name, int yearOfBirth) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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
}