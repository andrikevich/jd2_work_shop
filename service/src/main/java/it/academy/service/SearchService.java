package it.academy.service;

import it.academy.dao.ProductDao;
import it.academy.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    ProductDao productDao;

    public List<Product> searchProduct(String param){
    return productDao.findAllProducts().stream()
            .filter(x->x.getProductName().contains(param) || x.getDescription().contains(param))
            .collect(Collectors.toList());
    }
}
