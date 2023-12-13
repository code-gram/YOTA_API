package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Competency;

@Repository
public interface CompetencyRepository extends JpaRepository<Competency,Long>{

	Competency findByName(String name); 
}
