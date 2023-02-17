package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.AssociateDetails;

/**
 * AssociateDetailsService will be performing business logic related to Associates
 * @author nitin.chougale
 *
 */
public interface AssociateDetailsService {

	
	/**
	 * This service is to self register associate.
	 * @param register
	 */
	AssociateDetails selfRegister(AssociateDetails register);
	
	/**
	 * It gives list of all the registered associates.
	 */
	List<AssociateDetails> getAllAssociates();
	
	/**
	 * This service gives particular associate searched by id
	 * @param id
	 */
	AssociateDetails getAssociate(long id);

	/**
	 * Service is to update the associate details.
	 * @param associate
	 */
	AssociateDetails updateAssociate(AssociateDetails associate);
	
	/**
	 * This service is to delete the associate.
	 * @param id
	 */
	void deleteAssociate(long id);
	
	/**
	 * This service is to search the associate.
	 * @param keyword
	 */
	List<AssociateDetails> searchAssociate(String keyword);
}
