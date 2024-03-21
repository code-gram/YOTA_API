package com.yash.yotaapi.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Make sure this import is referencing the correct Training class
import com.yash.yotaapi.domain.Training;

/**
 * Repository interface for Training entity. Provides methods to interact with
 * Training entities in the database.
 * 
 * @author raghav.muchhal
 */
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

	/**
	 * Finds training with names containing the given keyword.
	 *
	 * @param keyword The keyword to search for in training names.
	 * @return List of training whose names contain the given keyword.
	 */
	List<Training> findByTrainingNameContaining(String keyword);

	/**

	 * Finds trainings between the given start and end dates.
	 *
	 * @param startDate The start date of the range.
	 * @param endDate   The end date of the range.
	 * @return List of trainings whose dates fall within the given range.
	 */
	@Query(value = "SELECT t FROM Training t WHERE t.startDate >= :startDate AND t.endDate <= :endDate")
	List<Training> findByDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}