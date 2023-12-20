package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Competency;

/**
* CompetencyRepository will perform all the CRUD Operations on Competency. 
* In case if any customization is required on CRUD operations, it can be done here. 
* @author gaurav.patil
*/
@Repository
public interface CompetencyRepository extends JpaRepository<Competency,Long>{

	/**
	 * findByName will give the competency details based on given name.
	 * @param competencyName
	 */
	Competency findByName(String competencyName); 
}
