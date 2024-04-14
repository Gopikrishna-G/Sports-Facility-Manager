package com.Sports.demo.Repo;

import com.Sports.demo.models.OwnerFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ownerfacilityrepo extends JpaRepository<OwnerFeatures, Integer> {
    // You can add custom query methods here if needed

}