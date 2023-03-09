package org.example;

import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().
                addAnnotatedClass(Person.class).
                addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//1 создать человека и паспорт
//            Person person = new Person("Test person", 50);
//            Passport passport = new Passport(123456);
//            person.setPassport(passport);
//            session.save(person);

//2 имя человека по id паспорта
//            Passport passport = session.get(Passport.class,1);
//            System.out.println(passport.getPerson().getName());


//3 поменять номер пасспорта
            Person person = session.get(Person.class, 1);
            person.getPassport().setPassportNumber(7777);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }

}
