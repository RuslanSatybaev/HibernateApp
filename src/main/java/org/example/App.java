package org.example;

import org.example.models.Item;
import org.example.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = new Person("Test cascading", 35);
            person.addItem(new Item("Test cascading item"));
            person.addItem(new Item("Test cascading item2"));
            person.addItem(new Item("Test cascading item3"));
            session.save(person);

            session.getTransaction().commit();
        }
    }
}
