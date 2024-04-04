package com.Sports.demo.models;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {
    @NotEmpty(message="The Username is required")
    private String username;

    @NotEmpty(message="The Full name is required")
    private String name;

    private int id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotEmpty(message="The Password is required")
    private String password;

    @NotEmpty(message="The Email is required")
    private String email;

    private String phoneNo;
    private String city;

    // Constructors, getters, setters, etc.
}
