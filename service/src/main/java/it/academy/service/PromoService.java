package it.academy.service;

import it.academy.dao.ProductDao;
import it.academy.dao.PromoDao;
import it.academy.model.Promo;
import it.academy.service.dto.PromoAndProductDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Service("promoService")
@PropertySource("classpath:app.properties")
public class PromoService {

    @Value("${promo.service.name}")
    private  String name;

    @Autowired
    @Qualifier("promoDaoImpl")
    private PromoDao promoDao;
    @Autowired
    private ProductDao productDao;

    public  void printPromo(){
        List<Promo> list = promoDao.findAllPromo();
        System.out.println("Hi I have promo for you");
        list.forEach(System.out::println);
    }

    @Transactional
    public static PromoService create(){
        System.out.println("method create()");
        return new PromoService();
    }

    public  void init(){
        this.name = "promoService3";
        System.out.println("Call init()");
    }


    public  void  destroy(){
        System.out.println("Call destroy()");
    }


    public  List<Promo> findAllPromo(){
        return promoDao.findAllPromo();
    }

    @Transactional
    public PromoAndProductDto findPromoAndProduct() {
        PromoAndProductDto dto = new PromoAndProductDto();
        int promoCount = promoDao.getPromoCount();
        int productCount =  productDao.getProductCount();
        dto.setPromoCount(promoCount);
        dto.setProductCount(productCount);
        return dto;
    }
}
