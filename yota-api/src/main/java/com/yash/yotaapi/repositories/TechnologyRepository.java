package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    Technology findByTechnology(String technology);

    @Query("select tech from Technology tech where tech.id=?1")
    Optional<Technology> findTechnologyById(Long techId);

    @Query("SELECT TECH FROM Technology TECH")
    List<Technology> getAllTechnologies();
}
