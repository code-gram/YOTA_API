package com.yash.yotaapi.repository;
/**
 * Repository layer will communicate with DB layer
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yash.yotaapi.domain.TechnologyMaster;

/**
 * ParentTechnologyRepository will perform all the CRUD Operations on ParentTechnology. 
 * In case if any customization is required on CRUD operations, it can be done here and 
 * Always extend JPARepository with Type,SerialVersionId
 * @author pankaj.ssharma
 *
 */
public interface TechnologyMasterRepository extends JpaRepository<TechnologyMaster, Long>{

	/**
	 * deleteByName method will delete the ParentTechnology by name
	 * @param name of the technology to be deleted
	 * @return true if ParentTechnology is deleted, otherwise should throw exception
	 */
	boolean deleteByName(String name);

	/**
	 * getByName method will give the ParentTechnology detail by name.
	 * @param name of the ParentTechnology to be searched
	 * @return ParentTechnology object if found otherwise null
	 */
	TechnologyMaster getByName(String name);

	/**
	 * getByNameContaining method will be used to search ParentTechnology based on the keyword provided
	 * @param keyword to be searched in ParentTechnology
	 * @return List of ParentTechnologies if found otherwise null
	 */
	List<TechnologyMaster> getByNameContaining(String keyword);
	/**
	 * findAll method here is customized according to status of that Technology 
	 * @return List of ParentTechnologies if status is false it will return in form of List But if it true then it won't return that Technology
	 */
	@Query(value = "SELECT * FROM Parent_Technology e WHERE e.status=false ",nativeQuery = true) 
	List<TechnologyMaster> findAll();

}
