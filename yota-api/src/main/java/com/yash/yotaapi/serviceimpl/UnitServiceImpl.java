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
 * This class provides business logic related to the management of Unit in the system.
 *
 * @author pravin.navarkar
 */
@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Override
    @Transactional
    public Unit addUnit(Unit unit) {
        Unit existingUnit = unitRepository.findByUnitName(unit.getUnitName());
        if (existingUnit == null) {
            return unitRepository.save(unit);
        } else {
            throw new UnitAlreadyExistsException("Unit already exists!!");
        }
    }

    @Override
    public List<Unit> getAllUnit() {
        return unitRepository.findAll();
    }

    @Override
    public void deleteUnitById(Long unitId) {
        Optional<Unit> unitMasterOptional = unitRepository.findById(unitId);
        if (unitMasterOptional.isPresent()) {
            unitRepository.deleteById(unitId);
        } else {
            throw new UnitNotFoundException("Unit not found with ID: " + unitId);
        }
    }

    @Override
    public Unit getUnitById(long unitId) {
        Optional<Unit> unitMasterOptional = unitRepository.findById(unitId);
        if (unitMasterOptional.isPresent()) {
            return unitMasterOptional.get();
        } else {
            throw new UnitNotFoundException("Unit not found with ID: " + unitId);
        }
    }

    @Override
    public Unit updateUnit(long id, Unit unitMaster) {
        Optional<Unit> unitMasterDetails = unitRepository.findById(id);
        if (unitMasterDetails.isPresent()) {
            Unit existingUnitMaster = unitMasterDetails.get();
            existingUnitMaster.setUnitName(unitMaster.getUnitName());
            existingUnitMaster.setShortDescription(unitMaster.getShortDescription());
            return unitRepository.save(existingUnitMaster);
        } else {
            throw new UnitNotFoundException("Unit not found with id:" + id);
        }
    }

    @Override
    public Unit searchUnit(String unitName){
        Unit searchedUnits = unitRepository.findByUnitName(unitName.toUpperCase());
        if(searchedUnits == null){
            throw new UnitNotFoundException("Unit:"+unitName+ " does not exists!!");
        }
        return searchedUnits;
    }
}
