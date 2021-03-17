package it.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@EnableSwagger2
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class ApiBootApp {

    public static void main(String[] args) {
        SpringApplication.run(ApiBootApp.class,args);
    }
}
