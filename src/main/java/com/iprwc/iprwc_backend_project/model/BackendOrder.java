package com.iprwc.iprwc_backend_project.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class BackendOrder {

    @Id
    private String id;

    private String name;
    private String customer;

    private String customerName;

    private String email;

    private String address;

    public BackendOrder() {
        // Default constructor required by JPA
    }

    public BackendOrder(String id, String name, String customer, String customerName, String email, String address) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}