package com.example.demospringbootpostgresql.controller;


import com.example.demospringbootpostgresql.models.Product;
import com.example.demospringbootpostgresql.repositories.ProductRepository;
import com.example.demospringbootpostgresql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService service;
    @GetMapping
    public List<Product> findAll() {
        return this.service.getAllProducts();

    }
//    @GetMapping("/name/{name}")
//    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {
//        return new ResponseEntity<>(productRepository.findByName(name), HttpStatus.OK);
//    }

    @GetMapping("/id/{id}")
    public Product getById(@PathVariable int id) {
        return this.service.getProductById(id);
    }

    @GetMapping("/price/{price}")
    public Product getByPrice(@PathVariable double price) {
        return this.service.getProductByPrice(price);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {

        return this.service.addProduct(product);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return this.service.updateProduct(id, product);


    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        this.service.deleteProduct(id);
    }

}
