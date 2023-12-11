package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Training;

/**
 * This Repository interface is used for managing {@link Training} entity in the database.
 *
 * This interface extends the Spring Data JPA {@link JpaRepository} interface,
 * providing CRUD (Create, Read, Update, Delete) operations for the {@link Training} entity.
 *
 * @Repository Indicates that this interface is a Spring Data repository.
 *
 * @author pravin.navarkar
 */
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    /**
     * Retrieves a {@link Training} entity from the database based on the provided training name.
     *
     * This method queries the database to find a training with the specified name.
     *
     * @param name The name of the training to be retrieved.
     * @return A {@link Training} entity if found, otherwise {@code null}.
     */
    Training findByName(String name);
}
