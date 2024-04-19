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

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id") // Name of the column in the booking table
    private User user;

    private Date startTime;
    private Date endTime;

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public SportsFacility getSportsFacility() {
        return sportsFacility;
    }

    public void setSportsFacility(SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
