package it.academy.web.controller;

import it.academy.service.dto.PromoAndProductDto;
import it.academy.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.logging.Logger;

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @Autowired
    PromoService promoService;

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(  Model model){
                logger.info("In home controller,  searchParam: ");
        model.addAttribute("promoList", promoService.findAllPromo());
        PromoAndProductDto dto =
                promoService.findPromoAndProduct();

        model.addAttribute("promoAndProduct", dto);
        return "index";
    }


}
