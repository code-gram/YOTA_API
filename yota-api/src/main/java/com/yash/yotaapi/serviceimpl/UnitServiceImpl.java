package com.yash.yotaapi.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.yash.yotaapi.domain.Unit;
import com.yash.yotaapi.exception.ParentUnitException;
import com.yash.yotaapi.exception.ParentUnitNotFoundException;
import com.yash.yotaapi.repository.UnitRepository;
import com.yash.yotaapi.service.UnitService;



/**
 * This is service layer class for Parent Unit to write business logic
 */
@Service
public class UnitServiceImpl implements UnitService{

	/**
	 * ParentUnitRepository is used to interact service layer with repository layer.
	 * 
	 */
	@Autowired
	private UnitRepository unitRepository;

	/**
	 * This method is for save ParentUnit to DB through repository layer
	 */
	@Override
	public Unit save(Unit unit) {
		try {
			unit.setId(unit.getId());
			return unitRepository.save(unit);
		} catch (DataIntegrityViolationException e) {
			throw new ParentUnitException("Unit with Name  " + unit.getId() + " already exists!!");
		}
	}

	/**
	 * This method is for get all ParentUnit from DB through repository layer.
	 */
	@Override
	public List<Unit> getAllUnits() {
		return unitRepository.findAll();
	}

	/**
	 * This method is for delete ParentUnit from DB through repository layer
	 */
	@Override
	@Transactional
	public void removeUnit(long id) {
		try {
			unitRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ParentUnitException("Unit with ID :" + id + " does not exist");
		}
		unitRepository.deleteById(id);

	}

	/**
	 * This method is for update ParentUnit to DB through repository layer
	 */
	@Override
	@Transactional
	public Unit updateUnit(Unit unit) {
		Unit existParentUnit = unitRepository.getByName(unit.getName());
		if (existParentUnit == null) {
			return unitRepository.save(unit);
		} else {
			existParentUnit.setName(unit.getName());
			existParentUnit.setShortDescription(unit.getShortDescription());
			unitRepository.save(existParentUnit);
		}
		return unit;
	}

	@Override
	public Unit updateUnit(Unit unit, long id) {

		Unit UnitDetails = unitRepository.findById(id).get();
		if (UnitDetails == null) {
			throw new ParentUnitNotFoundException(
					"Unit id: " + id + " is not present in UnitDetails ");
		} else {
			UnitDetails.setName(unit.getName());
			UnitDetails.setShortDescription(unit.getShortDescription());

			Unit updateUnit = unitRepository.save(UnitDetails);
			return updateUnit;
		}
	}

	/**
	 * This method is for get ParentUnit from DB through repository layer on
	 * basis of name only.
	 */
	@Override
	public Unit getUnit(long id) {
		Unit unit =unitRepository.findById(id).get();
		if (unit == null) {
			throw new ParentUnitNotFoundException("Unit with name : " + id + " does not exist");
		}
		return unit;
	}

	/**
	 * This method is for get ParentUnit from DB through repository layer on
	 * basis of keyword only.
	 */
	@Override
	public List<Unit> searchUnit(String keyword) {
		List<Unit> list = unitRepository.getByNameContaining(keyword.toUpperCase());
		if (list.isEmpty()) {
			throw new ParentUnitNotFoundException(
					"Unit containing keyword  : " + keyword + " does not exist");
		}
		return unitRepository.getByNameContaining(keyword.toUpperCase());

	}
}
