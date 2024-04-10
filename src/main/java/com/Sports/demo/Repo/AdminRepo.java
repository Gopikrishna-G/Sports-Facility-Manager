package com.Sports.demo.Repo;

import com.Sports.demo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {

    Admin findByEmail(String email);
}
