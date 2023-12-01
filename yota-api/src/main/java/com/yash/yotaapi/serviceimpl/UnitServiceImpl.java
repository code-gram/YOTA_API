package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.domain.Unit;
import com.yash.yotaapi.exception.UnitAlreadyExistsException;
import com.yash.yotaapi.exception.UnitNotFoundException;
import com.yash.yotaapi.repository.UnitRepository;
import com.yash.yotaapi.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link UnitService} interface.
 * This class provides business logic related to the management of units in the system.
 *
 * @author pravin.navarkar
 */
@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitMasterRepository;

    /**
     * Adds a new unit to the unit master table.
     * <p>
     * This method checks if a unit with the given ID already exists in the unit master table.
     * If the unit does not exists, it is added to the table.If the unit already exists,
     * a {@link UnitAlreadyExistsException} is thrown.
     *
     * @param unit The unit to be added to the unit master table.
     * @return The added unit if it does not already exists.
     * @throws UnitAlreadyExistsException If the unit with the same ID already exists in the table.
     */
    @Override
    @Transactional
    public Unit addUnit(Unit unit) {
        Unit existingUnit = unitMasterRepository.findByName(unit.getName());
        if (existingUnit == null) {
            return unitMasterRepository.save(unit);
        } else {
            throw new UnitAlreadyExistsException("Unit already exists!!");
        }
    }

    /**
     * This method retrieves a list of all units stored in the unit master table.
     *
     * @return A list containing all units from the unit master table.
     */
    @Override
    public List<Unit> getAllUnit() {
        return unitMasterRepository.findAll();
    }

    /**
     * Deletes a unit from the unit master table based on its ID.
     * This method attempts to find a unit with the given ID in the unit master table.
     * If the unit is found, it is deleted. If the unit is not found, a {@link UnitNotFoundException}
     * is thrown.
     *
     * @param unitId The ID of the unit to be deleted.
     * @throws UnitNotFoundException If a unit with the specified ID is not found in the table.
     */
    @Override
    public void deleteUnitById(long unitId) {
        Optional<Unit> unitMasterOptional = unitMasterRepository.findById(unitId);
        if (unitMasterOptional.isPresent()) {
            unitMasterRepository.deleteById(unitId);
        } else {
            throw new UnitNotFoundException("Unit not found with ID: " + unitId);
        }
    }

    /**
     * Retrieves the details of a unit from the unit master table based on its ID.
     * This method attempts to find a unit with the given ID in the unit master table.
     * If the unit is found, its details are returned. If the unit is not found,
     * a {@link UnitNotFoundException} is thrown.
     *
     * @param unitId The ID of the unit to be retrieved.
     * @return The details of the unit with the specified ID.
     * @throws UnitNotFoundException If a unit with the specified ID is not found in the table.
     */
    @Override
    public Unit getUnitById(long unitId) {
        Optional<Unit> unitMasterOptional = unitMasterRepository.findById(unitId);
        if (unitMasterOptional.isPresent()) {
            return unitMasterOptional.get();
        } else {
            throw new UnitNotFoundException("Unit not found with ID: " + unitId);
        }
    }

    /**
     * Updates the details of a unit in the unit master table based on its ID.
     * This method attempts to find a unit with the given ID in the unit master table.
     * If the unit is found, its details are updated with the provided {@code unitMaster}.
     * If the unit is not found, a {@link UnitNotFoundException} is thrown.
     *
     * @param id         The ID of the unit to be updated.
     * @param unitMaster The updated details for the unit.
     * @return The updated unit if it exists.
     * @throws UnitNotFoundException If a unit with the specified ID is not found in the table.
     */
    @Override
    public Unit updateUnit(long id, Unit unitMaster) {
        Optional<Unit> unitMasterDetails = unitMasterRepository.findById(id);
        if (unitMasterDetails.isPresent()) {
            Unit existingUnitMaster = unitMasterDetails.get();
            existingUnitMaster.setName(unitMaster.getName());
            return unitMasterRepository.save(existingUnitMaster);
        } else {
            throw new UnitNotFoundException("Unit not found with id:" + id);
        }
    }
}
