package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.TrainingTypeMaster;

@Repository
public interface TrainingTypeMasterRepository extends JpaRepository<TrainingTypeMaster, Long> {

}
