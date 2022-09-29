package com.example.shoponlineapi.repository;

import com.example.shoponlineapi.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
