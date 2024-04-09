package com.Sports.demo.Repo;

import com.Sports.demo.models.FacilityOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityOwnerRepo extends JpaRepository<FacilityOwner,Integer> {
    FacilityOwner findByEmail(String email);
}
