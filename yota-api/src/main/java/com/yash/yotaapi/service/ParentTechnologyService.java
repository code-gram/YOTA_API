package com.yash.yotaapi.service;

import java.util.List;
import java.util.Map;

import com.yash.yotaapi.domain.ParentTechnology;

/**
 * ParentTechnologyService will be performing business logic related to ParentTechnology
 * @author pankaj.ssharma
 *
 */
public interface ParentTechnologyService {

	/**
	 * save method will save the ParentTechnology. If same technology is already available then it should throw relevant exception.
	 * @param technology to be saved
	 * @return Saved ParentTechnology, it should hold the id of the new ParentTechnology saved in DB
	 */
	ParentTechnology save(ParentTechnology technology);

	/**
	 * getAllTechs method will fetch all ParentTechnologies from DB
	 * @return List of ParentTechnology
	 */
	List<ParentTechnology> getAllTechs();

	/**
	 * removeTech method will remove the technology that is mentioned
	 * @param id of the ParentTechnology to be removed
	 *  true if removed, otherwise false, in case technology is not available then it should throw appropriate exception.
	 */
	void removeTech(long id);

	/**
	 * this updateTech method will update the technology detail of the existing technology
	 * @param technology to be updated
	 * @return Updated ParentTechnology
	 */
	ParentTechnology updateTech(ParentTechnology technology);

	/**
	 * this getTech method will get the technology detail from 
	 * DB based on its name only
	 * @return ParentTechnolgy
	 */
	ParentTechnology getTech(String name);
	
	/**
	 * This searchTech enables us to search free text get the list of technologies
	 * @param keyword in upper case
	 * @return if there are any ParentTechnology by that keyword
	 */
	List<ParentTechnology> searchTech(String keyword);

	Map<Object,Long> findTests();

}
