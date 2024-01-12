package com.iprwc.iprwc_backend_project.Dao;

import com.iprwc.iprwc_backend_project.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>
{
    Optional<Product> findByName(String name);
}
