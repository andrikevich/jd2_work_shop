package it.academy.Main;

import it.academy.model.Person;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;

public class Main {
//
//    public static void main(String[] args) {
//
//        //создаем регистр конфигураций
//        final StandardServiceRegistry standardServiceRegistry
//                = new StandardServiceRegistryBuilder()
//                           .configure("hibernate.cfg.xml")
//                           .build();
//
//        SessionFactory factory = new MetadataSources(standardServiceRegistry)
//                                                        .buildMetadata()
//                                                        .buildSessionFactory();
//        Person person = new Person();
//        person.setName("Natalia");
//        person.setSecondName("Ivanoca");
//        person.setDateOfBirth(Date.valueOf("1980-01-01"));
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.save(person);
//            tx.commit();
//        }
//        catch (Exception e) {
//            if (tx!=null) tx.rollback();
//            throw e;
//        }
//        finally {
//            session.close();
//        }
//
//
//    }
}
