package org.example;

import org.example.models.Passport;
import org.example.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Passport passport = session.get(Passport.class, 11);
            System.out.println(passport.getPerson());


            session.getTransaction().commit();
        }
    }
}
