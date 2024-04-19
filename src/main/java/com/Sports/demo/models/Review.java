package com.Sports.demo.models;
import com.Sports.demo.models.*;
import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bookingID",referencedColumnName = "booking_id") // Name of the column in the booking table
    private Booking booking;

    private int rating;
    private String comment;

    public int getReview_id() {
        return id;
    }

    public void setReview_id(int review_id) {
        this.id = review_id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 0 && rating <= 10) { // Ensure rating is between 0 and 5
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
