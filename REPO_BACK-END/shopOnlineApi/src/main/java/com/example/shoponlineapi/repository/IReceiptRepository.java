package com.example.shoponlineapi.repository;

import com.example.shoponlineapi.model.product.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IReceiptRepository extends JpaRepository<Receipt, Integer> {

    @Query(value = "select * from receipt where user_id = :id order by id desc limit :limit",nativeQuery = true)
    List<Receipt> getAllReceiptById(@Param("id") int id, @Param("limit") Integer limit);
}
