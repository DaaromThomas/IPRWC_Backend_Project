package com.iprwc.iprwc_backend_project.model;

import jakarta.persistence.*;

import java.awt.*;

@Entity
public class Product
{

    @Id
    private String id;

    private String name; // New field

    private Double cost;

    @Column(name = "image_data")
    private byte[] imageData;

    // Constructors, getters, and setters

    public Product(String id, String name, Double cost, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.imageData = imageData;
    }

    public Product() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
