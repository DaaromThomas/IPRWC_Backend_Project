package com.iprwc.iprwc_backend_project.model;

import jakarta.persistence.*;

@Entity
public class ProductInShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private BackendOrder order;

    @ManyToOne
    private Product product;

    private int quantity;

    public ProductInShoppingCart(BackendOrder order, Product product, int numberOfProducts) {
        this.order = order;
        this.product = product;
        this.quantity = numberOfProducts;
    }

    public ProductInShoppingCart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BackendOrder getOrder() {
        return order;
    }

    public void setOrder(BackendOrder order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int numberOfProducts) {
        this.quantity = numberOfProducts;
    }


}
