package com.yash.yotaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.yotaapi.domain.AssociateDetails;

/**
 * AssociateDetailsRepository will perform all the CRUD Operations on AssociateDetails. 
 * In case if any customization is required on CRUD operations, it can be done here. 
 * @author nitin.chougale
 */
public interface AssociateDetailsRepository extends JpaRepository<AssociateDetails, Long>{

	
	/**
	 * getByEmailIdContaining will give the list of associates using given keyword containing in Mail Id.
	 * @param keyword
	 */
	List<AssociateDetails> getByEmailIdContaining(String keyword);
	
	/**
	 * We can fetch associate using findById method.
	 * @param Long
	 */
	Optional<AssociateDetails> findById(String Long);
	
}
