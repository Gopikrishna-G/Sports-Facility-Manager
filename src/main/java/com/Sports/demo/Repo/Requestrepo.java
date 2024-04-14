package com.Sports.demo.Repo;

import com.Sports.demo.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Requestrepo extends JpaRepository<Request,Integer> {
    List<Request> findByUserId(Integer userId);
    List<Request> findByUserIdAndStatus(Integer userId, String status);
}
