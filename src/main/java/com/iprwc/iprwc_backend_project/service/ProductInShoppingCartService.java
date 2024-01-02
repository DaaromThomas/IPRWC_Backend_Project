package com.iprwc.iprwc_backend_project.service;

import com.iprwc.iprwc_backend_project.model.ProductInShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductInShoppingCartService
{
    ProductInShoppingCart save(ProductInShoppingCart productInShoppingCart);

    List<ProductInShoppingCart> findAll();

    List<ProductInShoppingCart> findByOrderId(String orderId);

    Optional<ProductInShoppingCart> findById(String id);

    void delete(ProductInShoppingCart productInShoppingCart);
}
