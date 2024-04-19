package com.Sports.demo.Repo;

import com.Sports.demo.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookrepo extends JpaRepository<Booking,Integer> {
    Booking findByUserId(Integer userId);
}
