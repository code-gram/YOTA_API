package com.yash.yotaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yash.yotaapi.domain.Unit;

/**
 * unit Repository layer will communicate with DB layer
 *@author chandana.nemade
 */
public interface UnitRepository extends JpaRepository<Unit , Long> {
	/**
	 * deleteByName method will delete the Parent Unit by name
	 * 
	 * @param name of the unit to be deleted
	 * @return true if Parent unit is deleted, otherwise should throw exception
	 */
	boolean deleteByName(String name);
	/**
	 * getByName method will give the Parent unit detail by name.
	 * 
	 * @param name of the ParentUnit to be searched
	 * @return ParentUnit object if found otherwise null
	 */
	Unit getByName(String name);
	/**
	 * getByName Containing method will be used to search ParentUnit based on the keyword provided
	 * 
	 * @param keyword to be searched in ParentUnit
	 * @return List of ParentUnit if found otherwise null
	 */
	List<Unit> getByNameContaining(String keyword);
	@Query(value = "select * from Unit where status=false ", nativeQuery = true)
	List<Unit> findAll();

}
