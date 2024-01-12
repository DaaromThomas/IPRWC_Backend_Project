package com.iprwc.iprwc_backend_project.service;

import com.iprwc.iprwc_backend_project.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService
{
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(String id);

    Optional<Product> findByName(String name);

    void delete(Product product);

}
