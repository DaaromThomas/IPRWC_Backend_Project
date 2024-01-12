package com.iprwc.iprwc_backend_project.service;

import com.iprwc.iprwc_backend_project.Dao.ProductInShoppingCartRepository;
import com.iprwc.iprwc_backend_project.model.ProductInShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInShoppingCartImpl implements ProductInShoppingCartService {

    private final ProductInShoppingCartRepository productInShoppingCartRepository;

    public ProductInShoppingCartImpl(ProductInShoppingCartRepository productInShoppingCartRepository) {
        this.productInShoppingCartRepository = productInShoppingCartRepository;
    }

    @Override
    public ProductInShoppingCart save(ProductInShoppingCart productInShoppingCart) {
        return this.productInShoppingCartRepository.save(productInShoppingCart);
    }

    @Override
    public List<ProductInShoppingCart> findAll() {
        return (List<ProductInShoppingCart>) this.productInShoppingCartRepository.findAll();
    }

    @Override
    public List<ProductInShoppingCart> findByOrderId(String orderId) {
        return (List<ProductInShoppingCart>) this.productInShoppingCartRepository.findByOrderId(orderId);
    }

    @Override
    public Optional<ProductInShoppingCart> findById(String id) {
        return this.productInShoppingCartRepository.findById(id);
    }

    @Override
    public void delete(ProductInShoppingCart productInShoppingCart) {
        this.productInShoppingCartRepository.delete(productInShoppingCart);
    }
}
