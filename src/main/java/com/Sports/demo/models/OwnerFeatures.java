package com.Sports.demo.models;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//import javax.persistence.*;

@Entity
@Table(name="facility_owner_features")
public class OwnerFeatures {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int facility_id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private FacilityOwner owner;

    @Column(unique = true) // Ensures that each location is unique
    private String location;
    private String game;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    private int cost;

    public int getId() {
        return facility_id;
    }

    public void setId(int id) {
        this.facility_id = id;
    }

    public FacilityOwner getOwner() {
        return owner;
    }

    public void setOwner(FacilityOwner owner) {
        this.owner = owner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public int getNumoffacility() {
        return numoffacility;
    }

    public void setNumoffacility(int numoffacility) {
        this.numoffacility = numoffacility;
    }

    private String facilityType;
    private int numoffacility;



    // Getters and setters
}
