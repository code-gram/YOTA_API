package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.UnitMaster;

@Repository
public interface UnitMasterRepository extends JpaRepository<UnitMaster,Long>{

}
