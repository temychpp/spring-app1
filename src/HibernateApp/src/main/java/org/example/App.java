package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


        try {
            session.beginTransaction();

//       1 ищем товары у человека
            Person person = session.get(Person.class, 3);
            System.out.println(person);
            List<Item> items = person.getItems();
            System.out.println(items);
            System.out.println();



            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }

}
