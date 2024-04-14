package com.Sports.demo.models;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
@Table(name="facility_owner_features")
public class OwnerFeatures {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private FacilityOwner owner;

    @Column(unique = true) // Ensures that each location is unique
    private String location;
    private String game;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
