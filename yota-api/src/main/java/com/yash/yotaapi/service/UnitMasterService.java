package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.UnitMaster;

import java.util.List;

/**
 * Service interface for managing units in the system.
 * Defines methods for adding, retrieving, updating, and deleting unit information.
 *
 * @author pravin.navarkar
 */
public interface UnitMasterService {
    /**
     * Adds a new unit to the system.
     * This method is responsible for adding a new unit with the provided details to the system.
     *
     * @param unitMaster The unit details to be added.
     * @return The added unit.
     */
    UnitMaster addUnit(UnitMaster unitMaster);

    /**
     * This method retrieves a list of all units from the table.
     *
     * @return A List containing all units in the system
     */
    List<UnitMaster> getAllUnit();

    /**
     * This method is responsible for deleting a unit with the specified ID from the system.
     *
     * @param id The ID of the unit to be deleted.
     */
    void deleteUnitById(long id);

    /**
     * This method retrieves and returns the details of a unit with the specified ID from the system.
     *
     * @param id The ID of the unit to be retrieved.
     * @return The details of the unit with the specified ID.
     */
    UnitMaster getUnitById(long id);

    /**
     * This method attempts to find a unit with the given ID in the system.
     *
     * @param id         The ID of the unit to be updated.
     * @param unitMaster The updated details for the unit.
     * @return The updated unit.
     */
    UnitMaster updateUnit(long id, UnitMaster unitMaster);
}
