package it.academy;


import it.academy.model.Promo;
import it.academy.service.PromoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.util.Arrays;

@Configuration
@ComponentScan("it.academy")
public class AppConfig {

    @Bean()
    Promo promo(){
        return new Promo();
    }



    public static void main(String[] args) throws InterruptedException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        System.out.println("----------------------------");
        PromoService promoService = context.getBean("promoService", PromoService.class);
        System.out.println(promoService);


    context.close();

    }
}
