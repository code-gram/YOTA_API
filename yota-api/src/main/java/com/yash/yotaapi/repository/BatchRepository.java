package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

}
