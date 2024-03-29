package com.yash.yotaapi.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.yash.yotaapi.domain.TechnologyMaster;
import com.yash.yotaapi.exception.ParentTechnologyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.exception.AssociateDetailsException;
import com.yash.yotaapi.exception.AssociateDetailsNotFoundException;
import com.yash.yotaapi.repository.AssociateDetailsRepository;
import com.yash.yotaapi.service.AssociateDetailsService;

/**
 * This is service layer class for Associate to write business logic.
 * 
 * @author nitin.chougale
 *
 */

@Service
public class AssociateDetailsServiceImpl implements AssociateDetailsService {

	@Autowired
	private AssociateDetailsRepository associateDetailsRepository;

	/**
	 * This method saves the Associate details through repository layer.
	 */
	@Override
	public AssociateDetails selfRegister(AssociateDetails associate) {
		try {
			if (associate.getEmailId().length() == 0) {
				throw new AssociateDetailsException("Email Id should not be empty.");
			}
			return associateDetailsRepository.save(associate);
		} catch (DataIntegrityViolationException e) {
			if (associate.getEmailId() != null) {
				throw new AssociateDetailsException(
						"Associate mail Id : " + associate.getEmailId() + " already exists!!");
			}
		}
		return associate;
	}

	/**
	 * This method returns all associates from db through repository layer.
	 */
	@Override
	public List<AssociateDetails> getAllAssociates() {
		return associateDetailsRepository.findAll();
	}

	/**
	 * This method deletes the selected associates using id.
	 */
	@Override
	@Transactional
	public void deleteAssociate(long id) {
		try
		{
			associateDetailsRepository.findById(id).get();
		}catch (NoSuchElementException e) {
			throw new AssociateDetailsException("Associate with Id:"+id+" does not exist.");
		}
		associateDetailsRepository.deleteById(id);
	}

	/**
	 * This method searches for associates using any keyword. This method uses the
	 * concept of free-text-searching,
	 */
	@Override
	public List<AssociateDetails> searchAssociate(String keyword) {
		List<AssociateDetails> list = associateDetailsRepository.getByEmailIdContaining(keyword);
		if (list.isEmpty()) {
			throw new AssociateDetailsNotFoundException("Associate with keyword " + keyword + " does not exist.");
		}
		return associateDetailsRepository.getByEmailIdContaining(keyword);
	}
	
	/**
	 * This method returns the associate using id.
	 */
	@Override
	public Optional<AssociateDetails> getAssociate(long id) {
		try{
			Optional<AssociateDetails> associate= associateDetailsRepository.findById(id);
			return associate;
			}
		catch(AssociateDetailsNotFoundException e) {
			throw new AssociateDetailsNotFoundException("Associate with ID : "+ id +" does not exist");
			}
		}

	/**
	 * This method saves and updates the changes made in associate.
	 */
	@Override
	public AssociateDetails updateAssociate(AssociateDetails associate, long id) {

		AssociateDetails associateDetails = associateDetailsRepository.findById(id).get();
		if (associateDetails == null) {
			throw new AssociateDetailsNotFoundException(
					"AssociateDetails id: " + id + " is not present in AssociateDetails ");
		} else {
			associateDetails.setEmailId(associate.getEmailId());
			associateDetails.setPassword(associate.getPassword());

			AssociateDetails updatedAssociate = associateDetailsRepository.save(associateDetails);
			return updatedAssociate;
		}
	}

	
	/**
	 * By comparing to the id we can change our password using this method.
	 */
	@Override
	public Boolean updatePassword(HashMap<String, String> updatePassword) {
		try {
			System.out.println(updatePassword);
			Long id = Long.parseLong(updatePassword.get("associateId"));
			AssociateDetails associate = associateDetailsRepository.findById(id).get();
			associate.setPassword(updatePassword.get("password"));
			associateDetailsRepository.save(associate);
			return Boolean.TRUE;
		} catch (NoSuchElementException e) {
			throw new AssociateDetailsNotFoundException("Associate with ID " + updatePassword.get("associateId") + " does not exist.");
		} finally {
		}
	}
}
