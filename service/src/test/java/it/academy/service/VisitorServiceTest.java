package it.academy.service;


import it.academy.AppConfig;
import it.academy.dao.DaoConfiguration;
import it.academy.model.Visitor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class VisitorServiceTest {

    @Autowired
  VisitorService visitorService;

    @Before
    public void cleanUpDataBase(){
        visitorService.deleteAllVisitors();
    }

    @Test
    public void plusVisitor() throws InterruptedException {
       for(int i=0; i<1000;i++){
           new Thread(new Runnable() {
               @Override
               public void run() {
                   visitorService.saveVisitor();
               }
           }).run();
       }


        Assert.assertEquals(1000,visitorService.getMaxVisitor());
    }




}