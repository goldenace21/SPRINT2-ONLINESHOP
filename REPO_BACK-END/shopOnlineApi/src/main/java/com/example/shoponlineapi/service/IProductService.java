package com.example.shoponlineapi.service;

import com.example.shoponlineapi.model.product.Product;
import java.util.List;

public interface IProductService {
    List<Product> findAllAndSearchBYName(String name, Integer limit, Integer cate);

    Product findProductById(Integer id);
}
