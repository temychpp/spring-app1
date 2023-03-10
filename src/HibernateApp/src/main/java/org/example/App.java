package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().
                addAnnotatedClass(Movie.class).
                addAnnotatedClass(Actor.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
//1 добавляем фильм и двух актеров из этого фильма
//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

// 2 узнаем актеров из фильма и фильм у актеров
            Movie movie = session.get(Movie.class,1);
            System.out.println(movie.getActors());

            Actor actor1 = session.get(Actor.class, 1);
            Actor actor2 = session.get(Actor.class, 2);
            System.out.println(actor1.getMovies());
            System.out.println(actor2.getMovies());


                    session.getTransaction().commit();
        }

    }

}
