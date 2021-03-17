package it.academy.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "it.academy")
public class WebShopConfiguration {


    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        //в папке WEB-INF  становится не видно если зайти через  браузер указывая напрямую страницу
        //если положить по "/"  в корне,  то можно с браузера зайти например по адресу: main.jsp
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return  viewResolver;
    }

}
