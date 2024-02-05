package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Unit;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Service interface will perform business logic, method declaration for unit.
 *
 * @author pravin.navarkar
 */
public interface UnitService {
    /**
     * Adds a new unit to the system.
     * This method is responsible for adding a new unit with the provided details to the system.
     *
     * @param unit The unit details to be added.
     * @return The added unit.
     */
    Unit addUnit(Unit unit);

    /**
     * This method retrieves a list of all Unit from the table.
     *
     * @return A List containing all Unit in the system
     */
    List<Unit> getAllUnit();

    /**
     * This method is responsible for deleting a unit with the specified ID from the system.
     *
     * @param id The ID of the unit to be deleted.
     */
    void deleteUnitById(Long id);

    /**
     * This method retrieves and returns the details of a unit with the specified ID from the system.
     *
     * @param id The ID of the unit to be retrieved.
     * @return The details of the unit with the specified ID.
     */
    Unit getUnitById(long id);

    /**
     * This method attempts to find a unit with the given ID in the system.
     *
     * @param id         The ID of the unit to be updated.
     * @param unit The updated details for the unit.
     * @return The updated unit.
     */
    Unit updateUnit(long id, Unit unit);

    /**
     * This method used to search unit by unitName.
     *
     * @param unitName The unit name for find.
     * @return The searched unit.
     */
    Unit searchUnit(String unitName);
}
