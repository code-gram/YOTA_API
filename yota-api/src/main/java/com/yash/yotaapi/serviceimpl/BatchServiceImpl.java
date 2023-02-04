package com.yash.yotaapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.repository.BatchRepository;
import com.yash.yotaapi.service.BatchService;

/*This is service layer to impelment business logic here.
 * @author anil.shimpi
 * */
@Service
public class BatchServiceImpl implements BatchService{

	
	/* BatchRepository is used to interact service layer with resository layer. */
	
	@Autowired
	BatchRepository batchRepository;
	
	/* This method is used to store value to database */
	@Override
	public void createBatch(Batch batch) {
		
		batchRepository.save(batch);
	}

}
