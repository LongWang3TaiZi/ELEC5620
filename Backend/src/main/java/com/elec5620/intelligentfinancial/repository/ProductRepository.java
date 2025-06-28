package com.elec5620.intelligentfinancial.repository;

import com.elec5620.intelligentfinancial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBank_Id(Long bankId);
}
