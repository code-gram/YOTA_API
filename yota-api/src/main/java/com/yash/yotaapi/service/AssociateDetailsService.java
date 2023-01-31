package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.AssociateDetails;

/**
 * AssociateDetailsService will be performing business logic related to Associates
 * @author nitin.chougale
 *
 */
public interface AssociateDetailsService {

	
	/**
	 * @param Associate to be saved.
	 * @return It should hold the id of new registered associate.
	 */
	AssociateDetails selfRegister(AssociateDetails register);
	
	
}
