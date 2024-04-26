package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.AssignTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignTrainingRepository extends JpaRepository<AssignTraining, Integer> {
}