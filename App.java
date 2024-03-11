package ru.anna.RestApiConsumer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.anna.RestApiConsumer.entity.Actor;
import ru.anna.RestApiConsumer.entity.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) { // try with resources
            Session session = sessionFactory.openSession();
            session.beginTransaction();

//            Movie movie = new Movie("Криминальное чтиво", 1994); //добавление нового фильма и актеров
//            Actor actor1 = new Actor("Леонардо Дикаприо", 27);
//            Actor actor2 = new Actor("Пенелопа Круз", 34);
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2))); //тк лист пустой создаем его прямо тут.такой список неизменяемый
//            //Array.asList() - изменяемый, но не расширяемый
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie))); //работаем так чтобы показать что фильм только один
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.save(movie);
//            session.save(actor1); //каскадирование не настраивали поэтому сохраняем все вручную
//            session.save(actor2);

//            Movie movie = session.get(Movie.class,1); //вывод всех актеров фильма
//            System.out.println(movie.getActors());
//
//            Actor actor = session.get(Actor.class,2); //получаем все фильмы актера
//            System.out.println(actor.getMovies());

//            Movie movie = new Movie("Реквием по мечте", 1999); //добавляем новый фильм и связываем с актером
//            Actor actor = session.get(Actor.class, 2);
//            movie.setActors(new ArrayList<>(List.of(actor)));
//            actor.getMovies().add(movie);
//            session.save(movie);

            Actor actor = session.get(Actor.class,1);
            System.out.println(actor.getMovies());
            Movie movieToRemove = actor.getMovies().get(0); //получаем фильм который хотим удалить
            actor.getMovies().remove(0); // на актере вызываем список его фильмов и удаляем ненужный
            movieToRemove.getActors().remove(actor); // hascode equals надо реализовать. удаляем связь с другой стороны


            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
