package com.yash.yotaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.entity.Trainings;

@Repository
public interface TrainingRepository extends JpaRepository<Trainings, Long> {

}
