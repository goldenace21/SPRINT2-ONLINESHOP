package com.example.shoponlineapi.repository;

import com.example.shoponlineapi.model.product.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface IReceiptRepository extends JpaRepository<Receipt, Integer> {

}
