package com.Sports.demo.Repo;

import com.Sports.demo.models.FacilityOwner;
import com.Sports.demo.models.OwnerFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ownerfacilityrepo extends JpaRepository<OwnerFeatures, Integer> {
    // You can add custom query methods here if needed
    List<OwnerFeatures> findAllByOwnerId(int ownerId);

    List<OwnerFeatures> findByOwnerId(int ownerId);
}