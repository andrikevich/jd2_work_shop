package it.academy.web.controller;

import it.academy.model.Product;
import it.academy.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class SearchController {
    Logger logger = Logger.getLogger(SearchController.class.getName());

    @Autowired
    SearchService searchService;

    @GetMapping("/search")
    public String search(@RequestParam(value = "searchParam",required = false) String param,
                         Model model) {
        logger.info("In search controller,  searchParam: " + param);
        if (param != null && !"".equals(param.trim())) {
            List<Product> products = searchService.searchProduct(param);
            model.addAttribute("searchResult", products);
        }
        return "search-result";
    }
}
