package com.yash.yotaapi.service;

import java.util.List;

import javax.validation.Valid;

import com.yash.yotaapi.domain.Batch;

/*Batch service is a service layer provide the business logic
@author anil.shimpi*/

public interface BatchService {
	
	public void createBatch(Batch batch);

	public List<Batch> getAllDetails();

	public Batch getSingleBatchDetail(long bid);

	public Batch updateBatchDetails(long bid);

}
