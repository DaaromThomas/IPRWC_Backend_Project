package com.iprwc.iprwc_backend_project.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class BackendOrder {

    @Id
    private String id;

    private String name;
    private String customer;

    public BackendOrder() {
        // Default constructor required by JPA
    }

    public BackendOrder(String id, String name, String customer) {
        this.id = id;
        this.name = name;
        this.customer = customer;
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
}