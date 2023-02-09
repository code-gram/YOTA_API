package com.yash.yotaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Batch;

/*BatchRepository interface provide to perform all DB related operation and CRUD opertation.
 * @author anil.shimpi
 * */
@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

	/* perform CRUD operation */
	/* find batch details from database according to batch id entered by user . */
	public Optional<Batch> findById(long bid);

	/* find batch details from database according to batch name entered by user. */
	public List<Batch> findByBatchNameContaining(String keyword);

	/* Display batch details only status is false from database */
	@Query(value = "SELECT * from BATCH_MANAGEMENT b WHERE b.status =false", nativeQuery = true)
	public List<Batch> findAll();
	
	
	

}
