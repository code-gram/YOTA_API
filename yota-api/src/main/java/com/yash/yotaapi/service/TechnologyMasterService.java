package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.TechnologyMaster;

/**
 * ParentTechnologyService will be performing business logic related to
 * ParentTechnology
 * 
 * @author pankaj.ssharma
 *
 */
public interface TechnologyMasterService {

	/**
	 * save method will save the ParentTechnology. If same technology is already
	 * available then it should throw relevant exception.
	 * 
	 * @param technology to be saved
	 * @return Saved ParentTechnology, it should hold the id of the new
	 *         ParentTechnology saved in DB
	 */
	TechnologyMaster save(TechnologyMaster technology);

	/**
	 * getAllTechs method will fetch all ParentTechnologies from DB
	 * 
	 * @return List of ParentTechnology
	 */
	List<TechnologyMaster> getAllTechs();

	/**
	 * removeTech method will remove the technology that is mentioned
	 * 
	 * @param id of the ParentTechnology to be removed true if removed, otherwise
	 *           false, in case technology is not available then it should throw
	 *           appropriate exception.
	 */
	void removeTech(long id);

	/**
	 * this updateTech method will update the technology detail of the existing
	 * technology
	 * 
	 * @param technology to be updated
	 * @return Updated ParentTechnology
	 */
	TechnologyMaster updateTech(TechnologyMaster technology);

	/**
	 * updateClientDetails this getTech method will get the technology detail from
	 * DB based on its name only
	 * 
	 * @return ParentTechnolgy
	 */
	TechnologyMaster getTech(long id);

	/**
	 * This searchTech enables us to search free text get the list of technologies
	 * 
	 * @param keyword in upper case
	 * @return if there are any ParentTechnology by that keyword
	 */
	List<TechnologyMaster> searchTech(String keyword);

	TechnologyMaster updateTech(TechnologyMaster technology, long id);

}
