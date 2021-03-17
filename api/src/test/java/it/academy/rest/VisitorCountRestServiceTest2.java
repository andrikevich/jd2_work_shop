package it.academy.rest;

import it.academy.ApiBootApp;
import it.academy.RestTestConfiguration;
import it.academy.model.Visitor;
import it.academy.service.VisitorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


//@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApiBootApp.class)
@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application-integrationtest.properties")
public class VisitorCountRestServiceTest2 {

    public static final Logger log = Logger.getLogger(VisitorCountRestServiceTest2.class.getName());

    WebApplicationContext context;

    @Autowired
    VisitorService visitorService;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() {

        visitorService.deleteAllVisitors();
        Visitor visitor = new Visitor();
        visitor.setCount(0);
        visitor.setId("1");

        visitorService.saveVisitor(visitor);
    }



    @Test
    public void updateVisitorCountMultyThread() throws Exception {
        long timeBefore = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for(int i=0; i <1000; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        mockMvc
                                .perform(put("/visitor_count")).andReturn();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        String content= new String();

        executorService.shutdown();
       if(executorService.awaitTermination(20, TimeUnit.SECONDS)) {

           final MvcResult mvcResult = mockMvc
                   .perform(get("/visitor_count")).andReturn();
           content = mvcResult.getResponse().getContentAsString();
           log.info(content);

       }
       long diffTime = System.currentTimeMillis() - timeBefore;
       log.info("\n===>>> MultyThread: " + diffTime + "\n");
        assertEquals("1000", content);
    }


    @Test
    public void updateVisitorCountSinglThread() throws Exception {
        long timeBefore = System.currentTimeMillis();

        for(int i=0; i <1000; i++){
                        mockMvc
                                .perform(put("/visitor_count")).andReturn();

        }
        String content= new String();

            final MvcResult mvcResult = mockMvc
                    .perform(get("/visitor_count")).andReturn();
            content = mvcResult.getResponse().getContentAsString();
            log.info(content);


        long diffTime = System.currentTimeMillis() - timeBefore;
        log.info("\n===>>> SingleThread: " + diffTime + "\n");
        assertEquals("1000", content);
    }

    @Test
    public void readVisitorCount() {
    }
}