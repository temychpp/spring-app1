package com.temychp.spring.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


public class Person {

    private int id;
    @NotEmpty(message = "name should not be empty")
   // @Pattern(regexp = "[A-Z]\\w+\\s[A-Z]\\w+\\s[A-Z]\\w+",message = "incorrect name")
  //  @Size(min = 10, max = 30, message = "name should be between 10 and 30 characters")
    private String name;
    @Min(value = 1900, message = "Age should be greater than 0")
    @Max(value = 2023, message = "Age should be less than 2023")
   // @Size(min = 1900, max = 2023, message = "name should be between 1900 and 2023 years")
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
