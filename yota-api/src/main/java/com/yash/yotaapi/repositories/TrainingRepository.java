package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.entity.YotaUser;
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
    @Query(value = "insert into trainings_assign (trainings_id, assign_email_add) values (:trainingId, :emailId)", nativeQuery = true)
    Integer addAssignTraining(@Param("trainingId") Integer trainingId,
                              @Param("emailId") String emailId);

    @Query(value = "select count(trainings_id) from trainings_assign where trainings_id= :trainingId", nativeQuery = true)
    Integer registeredCount(@Param("trainingId") Integer trainingId);

    @Modifying
    @Query(value = "update Trainings t set t.registered_in_training= :registeredCount where t.id= :id", nativeQuery = true)
    Integer updateRegisteredCount(@Param("id") Integer id,
                                  @Param("registeredCount") Integer registeredCount);

    @Query(value = "select * from trainings_assign where trainings_id= :trainingId", nativeQuery = true)
    List<Object[]> assignedAssociated(@Param("trainingId") Integer trainingId);


    @Query(value = "select trainings_id from trainings_assign  trainings_id where trainings_id.assign_email_add=?1", nativeQuery = true)
    List<Long> getTrainingIdByEmailId(@Param("assign_email_add") String assign_email_add);

    @Query(value = "select * from trainings where id= :id", nativeQuery = true)
    Trainings getTrainingById(@Param("id") Long trainingId);

}
