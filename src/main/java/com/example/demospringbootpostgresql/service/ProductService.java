package com.example.demospringbootpostgresql.service;

import com.example.demospringbootpostgresql.models.Product;
import com.example.demospringbootpostgresql.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        List<Product> products = repository.findAll();
        return products;
    }

    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }


    public Product getProductById(Integer id) {
        return this.repository.getById(id);
    }

    public Product updateProduct(Integer id, Product product) {


        //get existing house in db
        Product existingProduct = this.repository.getById(id);
        //copy attributes from new house to existingHouse
        BeanUtils.copyProperties(product, existingProduct, "id");
        return this.repository.saveAndFlush(existingProduct);
    }
}
