package com.yash.yotaapi.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.exception.BatchIdException;
import com.yash.yotaapi.exception.BatchNotFoundException;
import com.yash.yotaapi.exception.DateInValidException;
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
	

	/* This method is used to store value into database */
	@Override
	public Batch createBatch(Batch batch) {

		batch.setBatchName(batch.getBatchName().toUpperCase());
		batch.setBatchIdentifier(batch.getBatchIdentifier().toUpperCase());

		try {

			return batchRepository.save(batch);

		} catch (DataIntegrityViolationException e) {
			throw new BatchNotFoundException(
					"Batch with Name " + batch.getBatchName().toUpperCase() + " is already exists!!");
		} catch (BatchIdException e) {
			throw new BatchIdException("Batch with batchIdentifier name " + batch.getBatchIdentifier().toUpperCase()
					+ " is already exists!!");
		}

	}

	/* This menthod us used to display all batch details from database. */
	@Override
	public List<Batch> getAllDetails() {

		List<Batch> batchDetails = batchRepository.findAll();
		if (batchDetails == null) {
			throw new BatchNotFoundException("Batch details does not exists !!");
		}

		return batchDetails;

	}

	/*
	 * This method display batch details for particular batch id entered user from
	 * database.
	 */
	@Override
	public Batch getBatch(String bIdentifier) {

		Batch detail = batchRepository.findByBatchIdentifier(bIdentifier.toUpperCase());

		if (detail == null) {

			throw new BatchIdException("Batch with id : " + bIdentifier + " does not exist");
		}

		return detail;

	}

	/*
	 * This method update batch details field specific batch id entered by user into
	 * database.
	 */
	@Override
	@Transactional
	public Batch updateBatchDetails(Batch batch) {

		Batch batchDetails = batchRepository.getByBatchIdentifier(batch.getBatchIdentifier());

		if (batchDetails == null) {

			return batchRepository.save(batchDetails);

		} else {
			batchDetails.setBatchName(batch.getBatchName());
			batchDetails.setBatchDescription(batch.getBatchDescription());
			batchRepository.save(batchDetails);
		}
		return batchDetails;
	}

	/*
	 * This method temporally hide batch details for mention batch id by user.
	 */
	@Override
	@Transactional
	public void removeBatchDetails(long batchId) {

		Batch batchDelete = batchRepository.findById(batchId).get();
		if (batchDelete != null) {
			batchRepository.deleteById(batchId);
		} else
			throw new BatchNotFoundException("Batch id: " + batchId + " is not present in Batch");
	}

	/* This method search batch and display details by keyword from database. */
	@Override
	public List<Batch> searchBatch(String keyword) {

		List<Batch> search = batchRepository.findByBatchNameContaining(keyword.toUpperCase());

		if (search.isEmpty()) {

			throw new BatchNotFoundException("Batch containing keyword  : " + keyword + " does not exist");
		}

		return batchRepository.findByBatchNameContaining(keyword.toUpperCase());
	}

	/* this method filter batch between start date and end date */
	@Override
	public List<Batch> getByStartDateAndEndDate(Date startDate, Date endDate) {
		long diff = endDate.getTime() - startDate.getTime();
		long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		System.out.println("Days: " + dayDiff);
		if (dayDiff < 1) {
			throw new DateInValidException("End date should be greater than start date");
		}
		List<Batch> search = batchRepository.findByDateBetween(startDate, endDate);
		if (search.isEmpty()) {
			throw new BatchNotFoundException("Batch containing startDate  : " + startDate + "and"
					+ "Batch containing endDate  : " + endDate + " does not exist");
		}
		return batchRepository.findByDateBetween(startDate, endDate);
	}

}
