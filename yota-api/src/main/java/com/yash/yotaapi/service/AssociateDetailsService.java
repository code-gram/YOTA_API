package com.yash.yotaapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
	 * Service is to update the associate details.
	 * @param associate
	 */
	AssociateDetails updateAssociate(AssociateDetails associate, long id);
	
	/**
	 * This service is to delete the associate.
	 * @param id
	 */
	void deleteAssociate(long id);
	
	/**
	 * This service is to get the associate details using id.
	 * @param id
	 * @return
	 */
	Optional<AssociateDetails> getAssociate(long id);
	
	/**
	 * This service is to search the associate.
	 * @param keyword
	 */
	List<AssociateDetails> searchAssociate(String keyword);

	/**
	 * This method is to update the password.
	 * @param updatePassword
	 */
	Boolean updatePassword(HashMap<String, String> updatePassword);

	//new change
	public List<AssociateDetails> getAssociateDataByBatchId(long batchId);
}
