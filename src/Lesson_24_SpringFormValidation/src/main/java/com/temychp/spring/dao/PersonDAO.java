package com.temychp.spring.dao;

import com.temychp.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom",22,"tom@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob",25,"bob@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike",30,"mike@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy",18,"katy@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Tem",35,"tem@gmail.com"));
    }


    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny().orElse(null);
    }



    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}










