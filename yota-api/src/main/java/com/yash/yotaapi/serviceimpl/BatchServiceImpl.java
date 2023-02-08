package com.yash.yotaapi.serviceimpl;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.exception.NoSuchElementFoundException;
import com.yash.yotaapi.repository.BatchRepository;
import com.yash.yotaapi.service.BatchService;

/*This is service layer to implement business logic here.
 * @author anil.shimpi
 * */
@Service
public class BatchServiceImpl implements BatchService {

	/* BatchRepository is used to interact service layer with resository layer. */

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private EntityManager entityManager;

	/* This method is used to store value into database */
	@Override
	public void createBatch(Batch batch) {

		batchRepository.save(batch);
	}
	
	/* This menthod us used to display all batch details from database. */
	  @Override 
	  public List<Batch> getAllDetails() {
		  return batchRepository.findAll();
	  
	  }
	 
		/*
		 * This method display batch details for particular batch id entered user from
		 * database.
		 */
	@Override
	public Batch getSingleBatchDetail(long bid) {
		Optional<Batch> batchDetail = batchRepository.findById(bid);
		return batchDetail.get();
	}

	/*
	 * This method update batch details field specific batch id entered by user into
	 * database.
	 */
	@Override
	public Batch updateBatchDetails(Batch batch, long batchId) {

		Optional<Batch> optBatchDetails = batchRepository.findById(batchId);
		Batch batchDetails = optBatchDetails.get();
		if (batchDetails == null) {

			batchRepository.save(batchDetails);
		} else {
			batchDetails.setBatchName(batch.getBatchName());
			batchDetails.setBatchDescription(batch.getBatchDescription());
			batchDetails.setStartDate(batch.getStartDate());
			batchDetails.setEndDate(batch.getEndDate());
			batchDetails.setCreatedAt(batch.getCreatedAt());
			batchDetails.setUpdatedAt(batch.getUpdatedAt());
			batchRepository.save(batchDetails);
		}
		return batchDetails;
	}

	/*
	 * This method delete batch details from database for mention batch id by user
	 * from database.
	 */
	@Override
	public void deleteBatchDetails(long batchId) {

		Batch batchDelete = batchRepository.findById(batchId).get();
		if (batchDelete != null) {
			batchRepository.deleteById(batchId);
		} else
			throw new NoSuchElementFoundException("BatchId: " + batchId + " is not present in Batch");
	}

	/* This method display batch details accourding to filter from database. */
	/*
	 * @Override public List<Batch> findAllFilter(boolean isDeleted) {
	 * 
	 * Session session = entityManager.unwrap(Session.class); Filter filter =
	 * session.enableFilter("deletedBatchFilter"); filter.setParameter("isDeleted",
	 * isDeleted); List<Batch> batchDetails = batchRepository.findAll();
	 * session.disableFilter("deletedBatchFilter"); return batchDetails; }
	 */



	/* This menthod search batch and display details by keyword from database. */
	@Override
	public List<Batch> searchBatch(String keyword) {
		 List<Batch> search=batchRepository.findByBatchNameContaining(keyword);
		 
		 if(search.isEmpty()) {
			 
			 throw new NoSuchElementFoundException("Batch containing keyword  : "+keyword+" does not exist");
		 }
			
		return batchRepository.findByBatchNameContaining(keyword);
	}

}
