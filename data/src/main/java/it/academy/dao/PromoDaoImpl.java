package it.academy.dao;

import it.academy.model.Promo;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class PromoDaoImpl implements PromoDao {


    Logger logger = Logger.getLogger(this.getClass().getName());



    SessionFactory sessionFactory;


    @Autowired
    public PromoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public PromoDaoImpl(@Value("true") boolean index) {
    }

    @Override
    @Transactional
    public List<Promo> findAllPromo() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from Promo");

        return query.list();
    }

    @Override
    @Transactional
    public int getPromoCount() {
        logger.info("In getPromoAndCount method");
        Session currentSession = sessionFactory.getCurrentSession();
        long result = (currentSession.createQuery("select count(promoId) from Promo",Long.class).list().get(0));
        logger.info("===>> Number of promoCount: "+ result);
        return (int)result;
    }

    @Override
    @Transactional
    public void create(Promo promo) {
        sessionFactory.getCurrentSession().saveOrUpdate(promo);
    }
}
