package com.example.shoponlineapi.controller;

import com.example.shoponlineapi.model.product.Category;
import com.example.shoponlineapi.model.product.Product;
import com.example.shoponlineapi.service.ICategoryService;
import com.example.shoponlineapi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;


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

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {
        List<Category> categories;
        categories = iCategoryService.getAll();
        if(categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
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

    @GetMapping("/delete")
    public ResponseEntity<Product> delete(@RequestParam("id") Integer id) {
        Product product;
        product = iProductService.findProductById(id);
        if(product==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        product.setDeleteStatus(true);
        iProductService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        iProductService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
