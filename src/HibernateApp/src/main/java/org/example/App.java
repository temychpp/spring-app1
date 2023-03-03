package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();


            session.createQuery("UPDATE Person set name='Test' where age<30").executeUpdate();

//            for (Person person:people
//                 ) {
//                System.out.println(person);
//                            }

            System.out.println();
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
