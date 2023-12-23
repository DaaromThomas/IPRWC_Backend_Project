package com.iprwc.iprwc_backend_project.Dao;

import com.iprwc.iprwc_backend_project.model.ProductInShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInShoppingCartRepository extends CrudRepository<ProductInShoppingCart, String>
{

}
