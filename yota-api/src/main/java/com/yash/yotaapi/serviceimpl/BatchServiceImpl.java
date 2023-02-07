package com.yash.yotaapi.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.repository.BatchRepository;
import com.yash.yotaapi.service.BatchService;

/*This is service layer to implement business logic here.
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

	@Override
	public List<Batch> getAllDetails() {
		return batchRepository.findAll();
		
	}

	@Override
	public Batch getSingleBatchDetail(long bid) {
		Optional<Batch> batchDetail=batchRepository.findById(bid);
		return batchDetail.get();
	}

	/*
	 * @Override public Batch updateBatchDetails(long bid) {
	 * 
	 * Optional<Batch> optupdateBatchDetails=batchRepository.findById(bid); Batch
	 * updateBatchDetails=optupdateBatchDetails.get(); return
	 * batchRepository.save(updateBatchDetails); }
	 */

}
