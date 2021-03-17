package it.academy.service;

import it.academy.dao.VisitorDao;
import it.academy.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class VisitorService {
    @Autowired
    VisitorDao visitorDao;

    public void saveVisitor(Visitor visitor){
        visitorDao.saveVisitor(visitor);
    }

    public int saveVisitor(){
        Visitor visitor = visitorDao.getVisitor();
        if (visitor == null) {
            visitor = new Visitor();
            visitor.setId("1");
            visitor.setCount(0);

        }
        int count = visitor.getCount();
        visitor.setCount(++count);
        return visitorDao.saveVisitor(visitor);
    }




    public int getMaxVisitor(){
        return  visitorDao.getMaxVisitor();
    }

    public void deleteAllVisitors(){
        visitorDao.deleteAll();
    }


   public Visitor getVisitor() {
       Visitor visitor = visitorDao.getVisitor();
       if (visitor == null) {
           visitor = new Visitor();
           visitor.setId("1");
           visitor.setCount(0);

       }
       return visitor;
   }
}
