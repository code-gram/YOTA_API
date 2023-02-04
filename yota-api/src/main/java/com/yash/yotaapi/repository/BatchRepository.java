package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Batch;

/*BatchRepository interface provide to perform all DB related operation and CRUD opertation.
 * @author anil.shimpi
 * */
@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
	
	/* perform CRUD operation */

}
