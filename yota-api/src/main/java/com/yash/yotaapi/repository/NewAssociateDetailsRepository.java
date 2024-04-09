package com.yash.yotaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.yotaapi.domain.NewAssociateDetail;
import com.yash.yotaapi.domain.Training;

/**
 * NewAssociateDetailsRepository will perform all the CRUD Operations on AssociateDetails. 
 * In case if any customization is required on CRUD operations, it can be done here. 
 * @author vishal.kanthariya
 */
public interface NewAssociateDetailsRepository extends JpaRepository<NewAssociateDetail, Long>{

	List<NewAssociateDetail> findByTraining(Training training);

	
}
