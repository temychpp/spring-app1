package com.temychp.spring.dao;

import com.temychp.spring.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional()
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional()
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional()
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional()
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person updatePerson = session.get(Person.class, id);
        updatePerson.setName(updatedPerson.getName());
        updatePerson.setAge(updatedPerson.getAge());
        updatePerson.setEmail(updatedPerson.getEmail());

    }

    @Transactional()
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Person.class, id));
    }
}