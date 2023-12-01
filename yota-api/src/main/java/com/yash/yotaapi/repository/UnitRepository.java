package com.yash.yotaapi.repository;

import com.yash.yotaapi.domain.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This Repository interface is used for managing {@link Unit} entity in the database.
 *
 * This interface extends the Spring Data JPA {@link JpaRepository} interface,
 * providing CRUD (Create, Read, Update, Delete) operations for the {@link Unit} entity.
 *
 * @Repository Indicates that this interface is a Spring Data repository.
 *
 * @author pravin.navarkar
 */
@Repository
public interface UnitRepository extends JpaRepository<Unit,Long>{
    /**
     * Retrieves a {@link Unit} entity from the database based on the provided unit name.
     *
     * This method queries the database to find a unit with the specified name.
     *
     * @param name The name of the unit to be retrieved.
     * @return A {@link Unit} entity if found, otherwise {@code null}.
     */
    Unit findByName(String name);
}
