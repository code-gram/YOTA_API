package com.yash.yotaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.yotaapi.domain.AssociateDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * AssociateDetailsRepository will perform all the CRUD Operations on AssociateDetails. 
 * In case if any customization is required on CRUD operations, it can be done here. 
 * @author nitin.chougale
 */
public interface AssociateDetailsRepository extends JpaRepository<AssociateDetails, Long>{



	//List<AssociateDetails> getByEmailIdContaining(String keyword);
	
	/**
	 * getByEmailIdContaining will give the list of associates using given keyword containing in Mail Id.
	 * @param keyword
	 */
	List<AssociateDetails> getByEmailIdContaining(String keyword);
	
	
	/**
	 * We can fetch associate using findById method.
	 * @param id
	 */
	Optional<AssociateDetails> findById(String id);

	//new change
	@Query(value ="select * from associate_details ad where ad.batchId=:batchId", nativeQuery = true)
	public List<AssociateDetails> getAssociateByBatchId(@Param("batchId")long batchId);

	//new change


}
