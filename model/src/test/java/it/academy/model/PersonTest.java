package it.academy.model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder
public class PersonTest  extends  BaseTest{



    @Test
    public void create(){
      //GIVEN-WHEN-THEN

        //Given
        Person person = new Person();
        person.setName("Natalia");
        person.setSecondName("Ivanoca");
        person.setDateOfBirth(Date.valueOf("1980-01-01"));
        person.setStatus(Status.UPDATED);
        person.setComments(new String[]{"Comment1", "Comment 2"});
        ShopUser shopUser = new ShopUser();
        shopUser.setUserName("n_ivanova");
        shopUser.setPassword("secret");
        person.setShopUser(shopUser);

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id = null;
        try {
            tx = session.beginTransaction();
            session.save(shopUser);
           id = session.save(person);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    //then
        Assert.assertNotNull(id);
    }

    @Test
    public void delete( ) {
      //given
        cleanInsert("PersonTest.xml");
        Session session = factory.openSession();
        Person person = session.get(Person.class, "3");


        //when
        Transaction tx = null;
        Serializable id = null;
        try {
            tx = session.beginTransaction();
            session.delete(person);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }

        //then
        Assert.assertNull(session.get(Person.class,"3"));
        session.close();
    }


    @Test
    public void queryShopUser(){
        cleanInsert("PersonShopUserTest.xml");
        Session session = factory.openSession();
        session.beginTransaction();
       List<ShopUser> resultList =  session.createQuery("select p.shopUser from Person as p", ShopUser.class).list();
       Assert.assertNotNull(resultList);
       Assert.assertEquals(3, resultList.size());
        session.close();
        factory.close();
    }

    @Test
    public void copyPerson(){
        cleanInsert("PersonTest.xml");
        Session session = factory.openSession();
        session.beginTransaction();
       int  count = session.createQuery(
               "insert into Person( personId, name, secondName, dateOfBirth, status)  select '4028b88177448dad0177448db34e0001',  name, secondName, dateOfBirth, status from Person where personId = :personId ")
               .setParameter("personId", "3").executeUpdate();

       session.getTransaction().commit();


        Assert.assertEquals(1, count);
        //  в ассерте нет метода (long, Long), поэтому приводим к примитиву через longValue()
        Assert.assertEquals(2, session.createQuery("select count(personId) from Person",Long.class).list().get(0).longValue());
        session.close();
        factory.close();
    }

}