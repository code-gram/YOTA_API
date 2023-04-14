package com.yash.yotaapi.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

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
	 * This method saves and updates the changes made in associate.
	 */
	@Override
	@Transactional
	public AssociateDetails updateAssociate(AssociateDetails associate) {

		AssociateDetails availableAssociate = associateDetailsRepository.findById(associate.getId()).get();
		if(availableAssociate==null) {
			return associateDetailsRepository.save(associate);
		}else {
			availableAssociate.setFirstName(associate.getFirstName());
			availableAssociate.setMiddleName(associate.getMiddleName());
			availableAssociate.setLastName(associate.getLastName());
//			availableAssociate.setEmailId(associate.getEmailId());
			availableAssociate.setContactNo(associate.getContactNo());
			//availableAssociate.setPassword(associate.getPassword());
			associateDetailsRepository.save(availableAssociate);

		//AssociateDetails availableAssociate = associateDetailsRepository.getById(associate.getId());
		if (availableAssociate.getEmailId() != null) {
			associate.setEmailId(availableAssociate.getEmailId());
		}
		if (availableAssociate.getPassword() != null) {
			associate.setPassword(availableAssociate.getPassword());
		}
		if (availableAssociate.getCreatedAt() != null) {
			associate.setCreatedAt(availableAssociate.getCreatedAt());
		}
		associate.setUpdatedAt(new Date());

		associateDetailsRepository.save(associate);
		return associate;
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
