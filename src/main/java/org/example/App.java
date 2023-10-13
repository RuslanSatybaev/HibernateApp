package org.example;

import org.example.models.one_to_many.Item;
import org.example.models.one_to_many.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println("Получили человека");
            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            person = (Person) session.merge(person);
            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();

            System.out.println(person.getItems());
        }
    }
}
