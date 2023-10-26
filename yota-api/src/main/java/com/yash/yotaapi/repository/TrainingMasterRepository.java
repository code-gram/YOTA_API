package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.TrainingMaster;

@Repository
public interface TrainingMasterRepository extends JpaRepository<TrainingMaster, Long> {

}
