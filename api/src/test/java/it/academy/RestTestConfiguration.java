package it.academy;

import it.academy.dao.ProductDao;
import it.academy.dao.VisitorDao;
import it.academy.model.Product;
import it.academy.service.ProductService;
import it.academy.service.VisitorService;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "it.academy")
@Profile("test")
public class RestTestConfiguration {

    @Bean
    @Primary
    public ProductService productService(){
        System.out.println(">>>Creating mockService");
        ProductService productService = Mockito.mock(ProductService.class);
        Mockito.when(productService.findAllProducts()).thenReturn((List.of(new Product())));
        return productService;
    }

    @Bean
    public ProductDao productDao(){
        return Mockito.mock(ProductDao.class);
    }


}
