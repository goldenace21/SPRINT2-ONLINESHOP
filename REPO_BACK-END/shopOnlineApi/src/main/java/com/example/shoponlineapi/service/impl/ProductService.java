package com.example.shoponlineapi.service.impl;

import com.example.shoponlineapi.model.product.Product;
import com.example.shoponlineapi.repository.IProductRepository;
import com.example.shoponlineapi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public List<Product> findAllAndSearchBYName(String name, Integer limit, Integer cate) {
        return iProductRepository.findAllAndSearchBYName(name, limit, cate);
    }

    @Override
    public Product findProductById(Integer id) {
        return iProductRepository.findProductById(id);
    }
}
