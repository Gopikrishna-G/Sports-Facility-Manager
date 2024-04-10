package com.Sports.demo.Repo;

import com.Sports.demo.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepo extends JpaRepository<Staff,Integer> {

    Staff findByEmail(String email);
}
