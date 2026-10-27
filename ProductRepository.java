package com.elkabani.mydb.repositories;

import com.elkabani.mydb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
