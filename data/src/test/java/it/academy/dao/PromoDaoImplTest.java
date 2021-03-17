package it.academy.dao;

import it.academy.model.Promo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@ContextConfiguration(classes = DaoConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PromoDaoImplTest {

    @Autowired
            @Qualifier("promoDaoImpl")
    PromoDao promoDao;

    @Test
    public void getPromoCount() {
        //Given
        Promo promo1= new Promo();
        promo1.setDescription("Promo1Desc");
        Promo promo2= new Promo();
        promo2.setDescription("Promo2Desc");
        promoDao.create(promo1);
        promoDao.create(promo2);
        //When
        int promoCount = promoDao.getPromoCount();
        assertTrue(promoCount > 2);

    }
}