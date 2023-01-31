package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.exception.AssociateDetailsException;
import com.yash.yotaapi.repository.AssociateDetailsRepository;
import com.yash.yotaapi.service.AssociateDetailsService;

/**
 * This is service layer class for Associate to write business logic.
 * @author nitin.chougale
 *
 */
@Service
public class AssociateDetailsServiceImpl implements AssociateDetailsService{
	
	@Autowired
	private AssociateDetailsRepository associateDetailsRepository;

	/**
	 * This method saves the Associate details through repository layer. 
	 */
	@Override
	public AssociateDetails selfRegister(AssociateDetails associate) {
		try {
			return associateDetailsRepository.save(associate);
		}
		catch (Exception e){
			throw new AssociateDetailsException("Associate mail Id : "+associate.getEmailId()+" already exists!!");
		}
	}

}
