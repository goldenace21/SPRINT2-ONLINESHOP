package com.example.shoponlineapi.repository;

import com.example.shoponlineapi.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product where name like %:name and delete_status = 0 and category_id = :cate order by date_create desc limit :limit ", nativeQuery = true)
    List<Product> findAllAndSearchBYName(@Param("name") String name, @Param("limit") Integer limit, @Param("cate") Integer cate);

    @Query(value = "select * from product where id = :id",nativeQuery = true)
    Product findProductById(@Param("id") Integer id);

}
