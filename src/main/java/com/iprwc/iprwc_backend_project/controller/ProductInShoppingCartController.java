package com.iprwc.iprwc_backend_project.controller;

import com.iprwc.iprwc_backend_project.model.BackendOrder;
import com.iprwc.iprwc_backend_project.model.FrontendOrder;
import com.iprwc.iprwc_backend_project.model.Product;
import com.iprwc.iprwc_backend_project.model.ProductInShoppingCart;
import com.iprwc.iprwc_backend_project.service.ProductInShoppingCartService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductInShoppingCartController
{

    private final ProductInShoppingCartService productInShoppingCartService;

    public ProductInShoppingCartController(ProductInShoppingCartService productInShoppingCartService) {
        this.productInShoppingCartService = productInShoppingCartService;
    }

    public void saveProducts(BackendOrder backendOrder, FrontendOrder frontendOrder){
        for (ProductInShoppingCart product : frontendOrder.getProducts()){
            ProductInShoppingCart productInShoppingCart = new ProductInShoppingCart(backendOrder, product.getProduct(), product.getQuantity());
            this.productInShoppingCartService.save(productInShoppingCart);
        }
    }

    public ProductInShoppingCart[] getProductsByOrderId(String orderId) {
        List<ProductInShoppingCart> productsInShoppingCart = this.productInShoppingCartService.findByOrderId(orderId);

        // Create an array of ProductInShoppingCart and use toArray(T[] a) method

        return productsInShoppingCart.toArray(new ProductInShoppingCart[0]);
    }

}
