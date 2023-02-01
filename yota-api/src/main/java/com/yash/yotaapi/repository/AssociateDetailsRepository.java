package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.yotaapi.domain.AssociateDetails;

/**
 * AssociateDetailsRepository will perform all the CRUD Operations on AssociateDetails. 
 * In case if any customization is required on CRUD operations, it can be done here. 
 * @author nitin.chougale
 *
 */
public interface AssociateDetailsRepository extends JpaRepository<AssociateDetails, Long>{

}
