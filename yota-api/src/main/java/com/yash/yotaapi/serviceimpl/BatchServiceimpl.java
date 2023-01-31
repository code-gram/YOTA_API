package com.yash.yotaapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.repository.BatchRepository;
import com.yash.yotaapi.service.BatchService;

@Service
public class BatchServiceimpl implements BatchService{

	
	//Autowired Repository
	@Autowired
	BatchRepository batchRepository;
	
	@Override
	public void createBatch(Batch batch) {
		
		batchRepository.save(batch);
	}

}
