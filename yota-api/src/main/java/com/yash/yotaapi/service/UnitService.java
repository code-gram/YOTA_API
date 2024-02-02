package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.Unit;

/**
 * ParentUnitService will be performing business logic related to ParentUnit
 */
public interface UnitService {
	/**
	 * save method will save the ParentUnit. If same Unit is already
	 * available then it should throw relevant exception.
	 * 
	 * @param Unit to be saved
	 * @return Saved ParentUnit, it should hold the id of the new
	 *         ParentUnit saved in DB
	 */
	Unit save(Unit unit);
	
	/**
	 * getAllUnits method will fetch all ParentUnit from DB
	 * 
	 * @return List of ParentUnit
	 */
	List<Unit> getAllUnits();
	
	/**
	 * removeUnit method will remove the Unit that is mentioned
	 * 
	 * @param id of the ParentUnit to be removed true if removed, otherwise
	 *           false, in case Unit is not available then it should throw
	 *           appropriate exception.
	 */
	void removeUnit(long id);
	
	/**
	 * this updateUnit method will update the Unit detail of the existing Unit
	 * 
	 * @param Unit to be updated
	 * @return Updated ParentUnit
	 */
	Unit updateUnit(Unit unit);
	
	/**
	 * updateUnitDetails this getUnit method will get the Unit detail from
	 * DB based on its name only
	 * 
	 * @return ParentUnit
	 */
	Unit getUnit(long id);
	
	/**
	 * This searchUnit enables us to search free text get the list of Unit
	 * 
	 * @param keyword in upper case
	 * @return if there are any ParentUnit by that keyword
	 */
	List<Unit> searchUnit(String Keyword);
	Unit updateUnit(Unit unit, long id);
	
}
