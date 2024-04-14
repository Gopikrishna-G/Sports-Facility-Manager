package com.Sports.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="staff")
public class Staff {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String fullname;
    private String facilityname;
    private String city;
    private String email;

    public String getPhoneno() { return phoneno; }

    public void setPhoneno(String phoneno) { this.phoneno = phoneno; }
    private String phoneno;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
