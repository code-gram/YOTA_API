package com.yash.yotaapi.repository;

import com.yash.yotaapi.domain.TechnologyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.UnitMaster;

/**
 * This Repository interface is used for managing {@link UnitMaster} entities in the database.
 *
 * This interface extends the Spring Data JPA {@link JpaRepository} interface,
 * providing CRUD (Create, Read, Update, Delete) operations for the {@link UnitMaster} entity.
 *
 * @Repository Indicates that this interface is a Spring Data repository.
 */
@Repository
public interface UnitMasterRepository extends JpaRepository<UnitMaster,Long>{

}
