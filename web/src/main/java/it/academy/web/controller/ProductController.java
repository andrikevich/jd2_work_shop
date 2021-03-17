package it.academy.web.controller;

import it.academy.model.Product;
import it.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ProductController {

  private static final  Logger logger = Logger.getLogger(ProductController.class.getName());
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public String addProduct(){
        return "add-product";
    }

    @GetMapping("/product/{id}")
    public String getProduct (Model model,@PathVariable("id") String id) {

        try {
            model.addAttribute("product", productService.findProductById(id));
            return "product";
        } catch (Exception e) {
           logger.log(Level.SEVERE,e.getMessage());
           model.addAttribute("errorMessage","Product not found");
            return "error";
        }

    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute Product product){
       logger.info("new product" + product);
       String productId = productService.saveNewProduct(product);
        return "redirect:/product/" + productId;
    }
}
