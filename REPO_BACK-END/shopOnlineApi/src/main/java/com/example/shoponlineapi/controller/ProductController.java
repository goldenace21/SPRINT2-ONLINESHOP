package com.example.shoponlineapi.controller;

import com.example.shoponlineapi.model.product.Product;
import com.example.shoponlineapi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/list")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    @GetMapping
    public ResponseEntity<List<Product>> findAllAndSearchBYName(@RequestParam(defaultValue = "") String name,
                                                                @RequestParam(defaultValue = "6") Integer limit,
                                                                @RequestParam Integer cate) {
        List<Product> products;
        products = iProductService.findAllAndSearchBYName(name,limit,cate);
        if(products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Product> findBYName(@RequestParam("id") Integer id) {
        Product product;
        product = iProductService.findProductById(id);
        if(product==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
