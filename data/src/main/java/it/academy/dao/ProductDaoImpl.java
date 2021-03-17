package it.academy.dao;

import it.academy.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

// лучше делать через конструктор Autowired
    // из-за чувствительности Session FActory, он объект тяжелый
   private  SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAllProducts() {
        Session session = sessionFactory.openSession();
        List<Product> lst = session.createQuery("from Product",Product.class).list();
        return lst;
    }

    @Override
    public Product read(String id) {
        Session session = sessionFactory.openSession();
        return session.get(Product.class,id);
    }

    @Override
    public String save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(product);
        return (String)id;
    }

    @Override
    public int getProductCount() {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(productId) from Product",Long.class);
        long result = (long) query.list().get(0);
        return (int)  result;
    }
}
