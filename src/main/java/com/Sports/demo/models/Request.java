package com.Sports.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private SportsFacility facility;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String slot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SportsFacility getFacility() {
        return facility;
    }

    public void setFacility(SportsFacility facility) {
        this.facility = facility;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
}
