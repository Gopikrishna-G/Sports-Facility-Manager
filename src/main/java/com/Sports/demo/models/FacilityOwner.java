package com.Sports.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="facilityOwner")
public class FacilityOwner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;
    private String phoneno;

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    private String password;
    private String fullname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getNumoffacility() {
        return numoffacility;
    }

    public void setNumoffacility(int numoffacility) {
        this.numoffacility = numoffacility;
    }

    private String email;
    private String City;
    private int numoffacility;

}
