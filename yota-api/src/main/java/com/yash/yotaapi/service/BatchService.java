package com.yash.yotaapi.service;

import java.util.Date;
import java.util.List;
import com.yash.yotaapi.domain.Batch;

/*Batch service is a service layer provide the business logic
@author anil.shimpi*/

public interface BatchService {

	public Batch createBatch(Batch batch);

	public Batch getBatch(long id);

	public Batch updateBatchDetails(Batch batch,long id);

	public void removeBatchDetails(long batchId);

	public List<Batch> getAllDetails();

	public List<Batch> searchBatch(String keyword);

	public List<Batch> getByStartDateAndEndDate(Date startDate, Date endDate);

}
