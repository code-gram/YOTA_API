package com.yash.yotaapi.repository;

import com.yash.yotaapi.domain.Nomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Nomination entity. Provides methods to interact with
 * Nomination entities in the database.
 * 
 * @author raghav.muchhal
 */
@Repository
public interface NominationRepository extends JpaRepository<Nomination, Long> {

	/**
	 * Finds nominations by employee ID.
	 *
	 * @param employeeId The ID of the employee.
	 * @return List of nominations associated with the given employee ID.
	 */
	List<Nomination> findByEmployeeId(Long employeeId);

	/**
	 * Finds nominations by employee name.
	 *
	 * @param employeeName The name of the employee.
	 * @return List of nominations associated with the given employee name.
	 */
	List<Nomination> findByEmployeeName(String employeeName);
}
