package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


        try {
            session.beginTransaction();

            Person person = new Person("Test Cascading", 33);
            Item item = new Item("Test Cascading Item", person);
            person.setItems(new ArrayList<>(Collections.singletonList(item)));

            session.save(person);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }

}
