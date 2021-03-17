package it.academy.dao;

import it.academy.model.Visitor;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class VisitorDaoImpl implements VisitorDao{
    @Autowired
    SessionFactory sessionFactory;



    @Override
    public  int saveVisitor(Visitor visitor) {
       sessionFactory.getCurrentSession().saveOrUpdate(visitor);
        return visitor.getCount();
        }




    @Override
    public int getMaxVisitor() {
        Session currentSession = sessionFactory.getCurrentSession();
        Visitor visitor = currentSession.get(Visitor.class, "1",LockMode.PESSIMISTIC_WRITE);

        if (visitor != null){
            int oldCount   =visitor.getCount();
            return oldCount;
        } else{
            return 0;
        }
    }


    @Override
    public void deleteAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("delete from Visitor");
        query.executeUpdate();


    }

    @Override
    public Visitor getVisitor() {
        Session currentSession = sessionFactory.getCurrentSession();
        Visitor visitor = currentSession.get(Visitor.class, "1", LockMode.PESSIMISTIC_WRITE);
        return visitor;
    }


}
