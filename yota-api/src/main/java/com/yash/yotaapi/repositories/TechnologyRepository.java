package com.yash.yotaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.entity.Technology;

@Repository
public interface TechnologyRepository  extends JpaRepository<Technology, Long>{

	Technology findByTechnology(String technology);

}
