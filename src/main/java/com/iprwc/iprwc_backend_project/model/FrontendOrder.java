package com.iprwc.iprwc_backend_project.model;

import jakarta.persistence.*;
import java.util.List;

public class FrontendOrder {

    private String id;

    private String name;
    private String customer;

    private ProductInShoppingCart[] products;

    public FrontendOrder(String id, String name, String customer, ProductInShoppingCart[] products) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ProductInShoppingCart[] getProducts() {
        return products;
    }

    public void setProducts(ProductInShoppingCart[] products) {
        this.products = products;
    }
}