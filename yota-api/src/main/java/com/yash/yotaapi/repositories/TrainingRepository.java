package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.Trainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Trainings, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into training_assign (trainings_id, assign_email_add) values (:trainingId, :emailId)", nativeQuery = true)
    Integer addAssignTraining(@Param("trainingId") Integer trainingId,
                              @Param("emailId") String emailId);

    @Query(value = "select count(trainings_id) from training_assign where trainings_id= :trainingId", nativeQuery = true)
    Integer registeredCount(@Param("trainingId") Integer trainingId);

    @Modifying
    @Query(value = "update Training t set t.registered_in_training= :registeredCount where t.id= :id", nativeQuery = true)
    Integer updateRegisteredCount(@Param("id") Integer id,
                                  @Param("registeredCount") Integer registeredCount);

    @Query(value = "select * from training_assign where trainings_id= :trainingId", nativeQuery = true)
    List<Object[]> assignedAssociated(@Param("trainingId") Integer trainingId);

}
