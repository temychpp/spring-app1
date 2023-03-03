package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person1 = new Person("Person1", 10);
            Person person2 = new Person("Person2", 20);
            Person person3 = new Person("Person3", 30);

            session.save(person1);
            session.save(person2);
            session.save(person3);

            session.get(Person.class, 1);
            session.get(Person.class, 3);

            System.out.println(person1.getName()+" "+ person1.getAge());
            System.out.println(person3.getName()+" "+ person3.getAge());

            System.out.println();
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
