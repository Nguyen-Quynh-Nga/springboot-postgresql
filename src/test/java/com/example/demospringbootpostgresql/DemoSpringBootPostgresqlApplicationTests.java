package com.example.demospringbootpostgresql;

import com.example.demospringbootpostgresql.models.Product;
import com.example.demospringbootpostgresql.repositories.ProductRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demospringbootpostgresql.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoSpringBootPostgresqlApplicationTests {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;


    @Test
    public void getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        given(repository.findAll()).willReturn(products);
        List<Product> expected = service.getAllProducts();
        assertEquals(expected, products);
        verify(repository).findAll();
    }

    @Test
    public void updateProduct() {
        Product product = new Product(1, "hat", 1, 2.0);
//        repository.save(product);
        Product newProduct = new Product();
        newProduct.setId(1);
        newProduct.setName("hattttt");
        newProduct.setQuantity(2);
        newProduct.setPrice(2.2);
        //Given
        given(repository.getById(product.getId())).willReturn(product);
        //When
        service.updateProduct(product.getId(), newProduct);
        //Then
        verify(repository).getById(product.getId());
        verify(repository).saveAndFlush(product);


    }

    @Test
    public void addProduct() {
        Product product = new Product();
        product.setName("hat");
        product.setQuantity(12);
        product.setPrice(1000.0);
        when(repository.save(ArgumentMatchers.any(Product.class))).thenReturn(product);
        Product created = service.addProduct(product);
        assertThat(created.getName()).isSameAs(product.getName());
        assertThat(created.getQuantity()).isEqualTo(product.getQuantity());
        assertThat(created.getPrice()).isEqualTo(product.getPrice());
        verify(repository).save(product);
    }

    @Test
    public void deleteProduct() {
        Product product = new Product(1,"shoes", 2, 2.0);
        when(repository.findById(product.getId())).thenReturn(Optional.of(product));
        service.deleteProduct(product.getId());
        verify(repository).deleteById(product.getId());
    }








}
