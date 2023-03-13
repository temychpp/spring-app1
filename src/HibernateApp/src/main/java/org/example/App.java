package org.example;

import org.example.model.Item;
import org.example.model.Person;
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
            Person person = session.get(Person.class, 1);
            System.out.println("Get person id=1");
            System.out.println(person.getItems());

            session.getTransaction().commit();
            // session.close()
            System.out.println(person.getItems());


        } finally {
            sessionFactory.close();
        }

    }

}
