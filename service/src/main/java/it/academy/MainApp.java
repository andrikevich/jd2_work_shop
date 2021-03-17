package it.academy;

import it.academy.service.PromoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) throws InterruptedException {
       //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);
        PromoService promoService = context.getBean("promoService",PromoService.class);
        System.out.println(promoService);
        promoService.printPromo();


        context.close();

    }
}
