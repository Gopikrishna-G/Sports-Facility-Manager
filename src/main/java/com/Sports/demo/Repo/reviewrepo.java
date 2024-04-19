package com.Sports.demo.Repo;

import com.Sports.demo.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Random;

public interface reviewrepo extends JpaRepository<Review,Integer> {

}
