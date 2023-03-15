package com.temychp.spring.dao;

import com.temychp.spring.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);

        //N+1 problem.
//        List<Person> people = session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//        for (Person person : people) {
//            System.out.println("Person " + person.getName() + " has: " + person.getItems());
//        }

        //N+1 problem. Solution
        Set<Person> people = new HashSet<>(session.createQuery("select p from Person p LEFT join FETCH p.items")
                .getResultList());
        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }
    }
}