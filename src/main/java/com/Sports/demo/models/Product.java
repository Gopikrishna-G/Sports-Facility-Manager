package com.Sports.demo.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="products")

public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String category;
    private double price;

    @Column(columnDefinition="TEXT")
    private String description;
    private Date createdAt;
    private String imageFileName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
