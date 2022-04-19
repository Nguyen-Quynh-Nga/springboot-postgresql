package com.example.demospringbootpostgresql.repositories;

import com.example.demospringbootpostgresql.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName(String name);
    List<Product> findProductByPrice(double price);

}
