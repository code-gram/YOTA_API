package com.yash.yotaapi.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.yash.yotaapi.domain.Batch;

/*BatchRepository interface provide to perform all DB related operation and CRUD operation.
 * @author anil.shimpi
 * */
@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

	/* find batch details from database according to batch name entered by user. */
	public List<Batch> findByBatchNameContaining(String keyword);

	/* Display batch details only status is false from database */
	@Query(value = "SELECT * from BATCH_MANAGEMENT b WHERE b.status =false", nativeQuery = true)
	public List<Batch> findAll();

	/*
	 * this method used to perform get batch for perticular bathc identifier
	 * specified by user.
	 */
	public Batch getByBatchIdentifier(String batchIdentifier);
	
	
	/*
	 * perform CRUD operation find batch details from database according to batch
	 * identifier name entered by user .
	 */
	  
	 public Batch findByBatchIdentifier(String batchIdentifier);
	 
	 /* find batch details from database between two dates. */
	@Query(value="SELECT * FROM batch_management b WHERE b.start_date >= :startDate AND b.end_date <= :endDate",nativeQuery=true)
	List<Batch> findByDateBetween(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
}
