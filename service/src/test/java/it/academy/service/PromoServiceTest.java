package it.academy.service;


import it.academy.AppConfig;
import it.academy.dao.PromoDaoImpl;
import it.academy.model.Promo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)

public class PromoServiceTest {

    @Autowired
    PromoService promoService;




    @Test
    public void findAllPromo() {

        //GIVEN
        PromoDaoImpl promoDaoMock = Mockito.mock(PromoDaoImpl.class);
        Promo promo =new Promo();
        promo.setDescription("Promo1Descript");
        Mockito
                    .when(promoDaoMock.findAllPromo())
                    .thenReturn(List.of(promo,promo));
        promoService.setPromoDao(promoDaoMock);

        //WHEN
        List<Promo> allPromo = promoService.findAllPromo();

        //THEN
        assertNotNull(allPromo);
        assertEquals(2,allPromo.size());
        assertEquals("Promo1Descript",allPromo.get(0).getDescription());
    }


}