package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


        try {
            session.beginTransaction();
//1 получаем человека и связанные сущности (Lazy)
//            Person person = session.get(Person.class, 1);
//            System.out.println("Get person id=1");
//            //Получаем связанные сущности (Lazy)
//            System.out.println(person.getItems());

//2 получаем товар и человека
//            Item item = session.get(Item.class, 1);
//            System.out.println("Get item id=1");
//            System.out.println(item.getOwner());

//3 получаем человека и связанные сущности (Eager)
//            Person person = session.get(Person.class, 1);
//            System.out.println("Get person id=1");
//            System.out.println(person.getItems());

//4 получаем человека и связанные сущности
//            Person person = session.get(Person.class, 1);
//            System.out.println("Get person id=1");
//            System.out.println(person);
            //подгружаем связанные ленивые сущности
//            Hibernate.initialize(person.getItems());

//5 получаем человека и связанные сущности
            Person person = session.get(Person.class, 1);
            System.out.println("Get person id=1");
            session.getTransaction().commit();
            System.out.println("session.close");

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("enter second session");
            person = (Person) session.merge(person);
            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();
            System.out.println("out second session");

            System.out.println(person.getItems());


        } finally {
            sessionFactory.close();
        }

    }

}
