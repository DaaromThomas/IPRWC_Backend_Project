package com.iprwc.iprwc_backend_project.Dao;

import com.iprwc.iprwc_backend_project.model.ProductInShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInShoppingCartRepository extends CrudRepository<ProductInShoppingCart, String>
{
    List<ProductInShoppingCart> findByOrderId(String order_id);
}
