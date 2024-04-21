package com.Sports.demo.models;

import jakarta.persistence.*;
import java.util.Date;
import com.Sports.demo.models.*;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int booking_id;

    @ManyToOne
    @JoinColumn(name = "facility_id",referencedColumnName = "id") // Name of the column in the booking table
    private SportsFacility sportsFacility;

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user;}

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id") // Name of the column in the booking table
    private User user;



    private enum Approved {yes, no}
    @Enumerated(EnumType.STRING)
    @Column(name = "approved")
    private Approved approved=Approved.no;

    public Approved getApproved() {
        return approved;
    }

    public void setApproved(Approved approved) {
        this.approved = approved;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public SportsFacility getSportsFacility() {
        return sportsFacility;
    }

    public void setSportsFacility(SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
    }
    private String slot;
    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }


}
