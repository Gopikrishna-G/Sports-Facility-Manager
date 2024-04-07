package com.Sports.demo.Repo;

import com.Sports.demo.models.SportsFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Facilityrepo extends JpaRepository<SportsFacility,Integer> {
}
