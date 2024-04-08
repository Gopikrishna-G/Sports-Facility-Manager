package com.Sports.demo.Repo;

import com.Sports.demo.models.SportsFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Facilityrepo extends JpaRepository<SportsFacility,Integer> {
    @Query("SELECT DISTINCT f.location FROM SportsFacility f")
    List<String> findDistinctLocations();

    List<SportsFacility> findByLocation(String location);
}
