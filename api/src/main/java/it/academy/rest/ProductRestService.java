package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.academy.model.Product;
import java.util.List;

@RestController
public class ProductRestService {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity readProduct (@PathVariable("id") String id){

        Product productById = (Product) productService.findProductById(id);
        if(productById != null) {
            return new ResponseEntity(productById, HttpStatus.OK);
        } else return new ResponseEntity(productById, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/products")
    public List<Product> readProducts (@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize){
        System.out.println(">>>from REST-Controller, pageNum:" + pageNum + " pageSize: " + pageSize);
        return productService.findAllProducts();
    }


    //Post  передается с id, т.к. индетификатор появляется как можно раньше во всей системе
    // в данном случает id  появляется в JS старнице,  в которой д.б. сгенерит uuid()
    @PostMapping("/products")
    @ApiOperation("for creating product")
    public ResponseEntity<Product> createProduct ( @RequestBody Product product){
        productService.saveNewProduct(product);
        return  new ResponseEntity(product, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable String id, @RequestBody Product product){
        return  new ResponseEntity(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct (@PathVariable String id){
        //productService.delete(id);
        return  new ResponseEntity( HttpStatus.NO_CONTENT);
    }
}
