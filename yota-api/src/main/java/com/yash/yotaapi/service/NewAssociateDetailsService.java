package com.yash.yotaapi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.NewAssociateDetail;

/**
 * NewAssociateDetailsService will be performing business logic related to Associates
 * @author nitin.chougale
 *
 */
public interface NewAssociateDetailsService {
	
	/**
	 * It gives list of all the registered associates.
	 */
	List<NewAssociateDetail> getAllAssociates(long id);
	
	public void saveExcel(MultipartFile file,long id);
	

}
